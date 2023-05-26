package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.dto.ToDoRequestDto;
import com.codingGenius.coding_genius.dto.ToDoResponseDto;
import com.codingGenius.coding_genius.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Time;

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    ToDoRepository toDoRepository;

    @Override
    public void save(ToDoRequestDto toDoRequestDto){
        try{
            ToDo toDo = toDoRepository.save(toDoRequestDto);
            return new ToDoResponseDto(toDo.getName(), toDo.getExpiration());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ToDoResponseDto> findByUserId(Long userId){
        try{
            List<ToDoResponseDto> toDoList = toDoRepository.findByUserId(userId);
            return toDoList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ToDoResponseDto update(Long id, ToDoRequestDto toDoRequestDto){
        try{
            Optional<ToDo> toDoData = toDoRepository.findById(id);
            if(toDoData.isPresent()){
                ToDo toDo = toDoData.get();
                toDo.setName(toDoRequestDto.getName());
                toDo.setExpiration(toDoRequestDto.getExpiration());
                return toDoRepository.save(toDo);
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

    @Override
    public List<ToDoResponseDto> sort(Long userId){
        try{
            List<ToDoResponseDto> toDoList = findByUserId(userId);
            List<Time> expList = new ArrayList<>();

            /*
            for(ToDoResponseDto toDo : toDoList){
                expList.add(toDo.getExpiration);
            }
            */

            //정렬하기
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
