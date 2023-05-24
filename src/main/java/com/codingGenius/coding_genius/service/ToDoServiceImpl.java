package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    ToDoRepository toDoRepository;

    @Override
    public ToDoResponseDto save(ToDoRequestDto toDoRequestDto){
        try{
            ToDo toDo = toDoRepository.save(toDoRequestDto);
            return new ToDoResponseDto(toDo.getName(), toDo.getExpiration());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ToDoResponseDto> findAll(){
        try{
            List<ToDoResponseDto> toDoList = toDoRepository.findAll();
            return toDoList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ToDo update(Long id, ToDoRequestDto toDoRequestDto){
        try{
            Optional<ToDo> toDoData = toDoRepository.findById(id);
            if(toDoData.isPresent()){
                ToDo toDo = toDoData.get();
                toDo.setName(toDoRequestDto.getName());
                toDo.setExpiration(toDoRequestDto.getExpiration());
                toDoRepository.save(toDo);
                return toDo;
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
            toDoRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
