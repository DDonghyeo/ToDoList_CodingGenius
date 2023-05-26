package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.repository.ToDoListRepository;
import com.codingGenius.coding_genius.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    ToDoListRepository toDoListRepository;

    @Override
    public void save(String email, ToDo toDo){
        try{
            ToDoList toDoLIst = new ToDoList(email, toDo);
            toDoListRepository.save(toDoLIst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ToDoList findAllByEmail(String email){
        try{
            toDoListRepository.findByEmail(email);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String email){
        try{
            Optional<ToDo> toDoData = toDoListRepository.findBy
            if(toDoData.isPresent()){
                ToDo toDo = toDoData.get();
                toDo.setName(toDoRequestDto.getName());
                toDo.setExpiration(toDoRequestDto.getExpiration());
                return toDoListRepository.save(toDo);
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id){
        try{
            toDoListRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
