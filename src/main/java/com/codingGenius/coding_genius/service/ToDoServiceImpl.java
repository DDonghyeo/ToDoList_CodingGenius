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

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    ToDoListRepository toDoListRepository;

    @Override
    public void save(String email, ToDoRequestDto toDoRequestDto){
        try{
            ToDoList toDoList = findByEmail(email);
            toDoList.getToDoArrayList().add(new ToDo(toDoRequestDto));
            toDoListRepository.save(new ToDoList(email, toDoList.getToDoArrayList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ToDoList findByEmail(String email){
        try{
            toDoListRepository.findByEmail(email);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String email, ToDoUpdateDto toDoUpdateDto){
        try{

            ToDoList toDoList = toDoListRepository.findByEmail(email);
            if(toDoList != null){
                ArrayList<ToDo> toDoArrayList = toDoList.getToDoArrayList();
                Iterator<ToDo> it = toDoArrayList.iterator();
                while(it.hasNext()){
                    ToDo toDo = it.next();
                    if(toDo.getName().equals(toDoUpdateDto.getOldName())){//여기서 업데이트하고 리포지토리 세이브
                        //todoarraylist에서 todo를 변경하고 todoarraylist를 email과 함께 todolist에 넣는다.
                        toDo.setName(toDoUpdateDto.getNewName());
                        toDo.setComplete(toDoUpdateDto.isComplete());
                        toDo.setExpiration(toDoUpdateDto.getExpiration());
                        it.remove();
                        toDoArrayList.add(toDo);
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
            ToDoList toDoList = toDoListRepository.findByEmail(email);
            ArrayList<ToDo> toDoArrayList = toDoList.getToDoArrayList();
            Iterator<ToDo> it = toDoArrayList.iterator();
            while(it.hasNext()){
                if(it.next().getName().equals(name)){
                    it.remove();
                    toDoListRepository.save(new ToDoList(email, toDoArrayList));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ToDo findOne(String email, String todoName){
        ArrayList<ToDo> toDoArrayList = findByEmail(email).getToDoArrayList();
        Iterator<ToDo> it = toDoArrayList.iterator();
        while(it.hasNext()){
            ToDo toDo = it.next();
            if(toDo.getName().equals(todoName)){
                return toDo;
            }
        }
        return null;
    }

}
