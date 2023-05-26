package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.domain.Work;
import com.codingGenius.coding_genius.dto.ToDoRequestDto;
import com.codingGenius.coding_genius.dto.WorkRequestDto;
import com.codingGenius.coding_genius.repository.ToDoListRepository;
import com.codingGenius.coding_genius.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    WorkRepository workRepository;

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    ToDoService toDoService;

    //email, todoName, workName, complete 받아옴
    //email, todoName으로 work를 넣을 todo를 찾음
    //todo의 work list에 work 추가
    //todolistrepository.save
    /*
    public void save(String email, WorkRequestDto workRequestDto){
        ToDo toDo = toDoService.findOne(email, workRequestDto.getTodoName());//work를 넣을 todo를 찾음
        Work work = new Work(workRequestDto.getWorkName(), workRequestDto.isComplete());//work 생성
        ArrayList<Work> workArrayList = toDo.getWorkArrayList();
        workArrayList.add(work);//todo의 work list에 work 추가
        ToDoList toDoList = toDoService.findByEmail(email);
        toDoListRepository.
    }
    */

    public ArrayList<Work> findWork(String email, String todoName){
        try{
            ToDo toDo = toDoService.findOne(email, todoName);
            return toDo.getWorkArrayList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
