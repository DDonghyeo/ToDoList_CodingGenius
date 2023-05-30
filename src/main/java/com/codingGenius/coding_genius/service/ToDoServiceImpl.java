package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.dto.ToDoRequestDto;
import com.codingGenius.coding_genius.dto.ToDoUpdateDto;
import com.codingGenius.coding_genius.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    ToDoListRepository toDoListRepository;

    @Override
    public void save(String email, ToDoRequestDto toDoRequestDto){
        try{
            Optional<ToDoList> toDoList = toDoListRepository.findByEmail(email);
            if (toDoList.isPresent()) { //있을 시
                ArrayList<ToDo> toDoArrayList = toDoList.get().getToDoArrayList();
                toDoArrayList.add(new ToDo(toDoRequestDto));
                toDoListRepository.save(new ToDoList(email, toDoArrayList));
            }else createTodo(email, toDoRequestDto); //없을 시
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTodo(String email, ToDoRequestDto toDoRequestDto) {
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();
        toDoArrayList.add(new ToDo(toDoRequestDto));
        ToDoList toDoList = new ToDoList(email, toDoArrayList);
        toDoListRepository.save(toDoList);
    }

    @Override
    public ToDoList findByEmail(String email){
        try{
            Optional<ToDoList> toDoList = toDoListRepository.findByEmail(email);
            if (toDoList.isPresent()) {
                return toDoList.get();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String email, ToDoUpdateDto toDoUpdateDto){
        try{

            Optional<ToDoList> toDoList = toDoListRepository.findByEmail(email);
            if(toDoList.isPresent()){
                ArrayList<ToDo> toDoArrayList = toDoList.get().getToDoArrayList();
                Iterator<ToDo> it = toDoArrayList.iterator();
                while(it.hasNext()){
                    ToDo toDo = it.next();
                    if(toDo.getName().equals(toDoUpdateDto.getOldName())){
                        //todoarraylist에서 todo를 변경하고 todoarraylist를 email과 함께 todolist에 넣는다.
                        int idx = toDoArrayList.indexOf(toDo);//인덱스 찾고
                        toDo.setName(toDoUpdateDto.getNewName());
                        toDo.setExpiration(toDoUpdateDto.getExpiration());
                        toDoArrayList.set(idx, toDo);//set
                        toDoListRepository.save(new ToDoList(email, toDoArrayList));
                        break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String email, String name){//email의 todo중에서 name을 찾아서 삭제
        try{
            Optional<ToDoList> toDoList = toDoListRepository.findByEmail(email);
            if (toDoList.isPresent()) {
                ArrayList<ToDo> toDoArrayList = toDoList.get().getToDoArrayList();
                Iterator<ToDo> it = toDoArrayList.iterator();
                while(it.hasNext()){
                    if(it.next().getName().equals(name)){
                        it.remove();
                        toDoListRepository.save(new ToDoList(email, toDoArrayList));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ToDo findOne(String email, String todoName){
        ArrayList<ToDo> toDoArrayList = findByEmail(email).getToDoArrayList();
        for (ToDo toDo : toDoArrayList) {
            if (toDo.getName().equals(todoName)) {
                return toDo;
            }
        }
        return null;
    }

    //todoname으로 todo찾기 complete 변경하고 저장
    //todo가 있는 todoarraylist 찾아서 지우고 추가
    //todolist에 todoarraylist 저장
    //리포지토리에 저장
    @Override
    public void complete(String email, String todoName){
        ToDoList toDoList = findByEmail(email);
        ArrayList<ToDo> toDoArrayList = toDoList.getToDoArrayList();
        Iterator<ToDo> it = toDoArrayList.iterator();
        while(it.hasNext()){
            ToDo element = it.next();
            if(element.getName().equals(todoName)){
                if(element.isComplete()){
                    element.setComplete(false);
                } else{
                    element.setComplete(true);
                }
                break;
            }
        }
        toDoListRepository.deleteByEmail(email);
        toDoListRepository.save(new ToDoList(email, toDoList.getToDoArrayList()));
    }

}
