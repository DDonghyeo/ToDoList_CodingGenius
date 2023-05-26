package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.dto.ToDoRequestDto;
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
    public void update(String email, ToDoRequestDto toDoRequestDto){
        try{
            ToDoList toDoList = toDoListRepository.findByEmail(email);
            if(toDoList != null){
                ArrayList<ToDo> toDoArrayList = toDoList.getToDoArrayList();
                Iterator<ToDo> it = toDoArrayList.iterator();
                while(it.hasNext()){
                    if(it.next().getName().equals(toDoRequestDto.getName())){
                        it.remove();
                        toDoListRepository.save(new ToDoList(email, toDoArrayList));
                        save(email, toDoRequestDto);
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
