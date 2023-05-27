package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.domain.Work;
import com.codingGenius.coding_genius.dto.WorkDeleteDto;
import com.codingGenius.coding_genius.dto.WorkRequestDto;
import com.codingGenius.coding_genius.dto.WorkUpdateDto;
import com.codingGenius.coding_genius.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    ToDoService toDoService;

    //email, todoName, workName, complete 받아옴
    //email, todoName으로 work를 넣을 todo를 찾음
    //todo의 work list에 work 추가
    //todolistrepository.save
    //email의 todo list에 todo 추가
    @Override
    public void save(String email, WorkRequestDto workRequestDto){
        try{
            ToDo toDo = toDoService.findOne(email, workRequestDto.getTodoName());//work를 넣을 todo를 찾음
            Work work = new Work(workRequestDto.getWorkName(), workRequestDto.isComplete(), workRequestDto.getMemo());//work 생성
            ArrayList<Work> workArrayList = toDo.getWorkArrayList();
            workArrayList.add(work);//todo의 work list에 work 추가
            ToDoList toDoList = toDoService.findByEmail(email);
            toDoList.getToDoArrayList().add(toDo);//todo list에 todo 추가
            toDoListRepository.save(toDoList);//중복될경우 update
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Work> findWork(String email, String todoName){
        try{
            ToDo toDo = toDoService.findOne(email, todoName);
            return toDo.getWorkArrayList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //todo 찾기. 원래 있던거 지우기. 추가하기.
    @Override
    public void update(String email, WorkUpdateDto workUpdateDto){
        try{
            ToDo toDo = toDoService.findOne(email, workUpdateDto.getTodoName());//work를 넣을 todo를 찾음
            //기존에 있던 work를 찾아서 지움
            ArrayList<Work> workArrayList = toDo.getWorkArrayList();
            Iterator<Work> it = workArrayList.iterator();
            while(it.hasNext()){
                if (it.next().getName().equals(workUpdateDto.getOldName())){
                    it.remove();
                }
            }
            save(email, new WorkRequestDto(workUpdateDto));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //todoname으로 todo찾기 workarraylist iterator로 접근, workname으로 찾기. 삭제하기. todo set workarraylist
    //repository.save(todolist): todolist의 todoarraylist에서 todoname으로 찾아서 원래있던거 지우고 새로만든거 넣기. repository save하기
    @Override
    public void delete(String email, WorkDeleteDto workDeleteDto){
        try{
            ToDo toDo = toDoService.findOne(email, workDeleteDto.getTodoName());
            ArrayList<Work> workArrayList = toDo.getWorkArrayList();
            Iterator<Work> it = workArrayList.iterator();
            while(it.hasNext()){
                if (it.next().getName().equals(workDeleteDto.getWorkName())){
                    it.remove();
                    break;
                }
            }
            //toDo.setWorkArrayList(workArrayList);

            ToDoList toDoList = toDoService.findByEmail(email);
            ArrayList<ToDo> toDoArrayList = toDoList.getToDoArrayList();
            Iterator<ToDo> it2 = toDoArrayList.iterator();
            while(it2.hasNext()){
                if(it2.next().getName().equals(workDeleteDto.getTodoName())){
                    it.remove();
                    toDoArrayList.add(toDo);
                }
            }
            toDoList.setToDoArrayList(toDoArrayList);
            toDoListRepository.save(toDoList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
