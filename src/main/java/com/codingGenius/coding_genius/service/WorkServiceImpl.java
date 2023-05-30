package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.domain.Work;
import com.codingGenius.coding_genius.dto.WorkCDDto;
import com.codingGenius.coding_genius.dto.WorkRequestDto;
import com.codingGenius.coding_genius.dto.WorkUpdateDto;
import com.codingGenius.coding_genius.repository.ToDoListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Slf4j
@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    ToDoService toDoService;

    @Override
    public void save(String email, WorkRequestDto workRequestDto){
        try{
            ToDoList toDoList = toDoService.findByEmail(email);
            ArrayList<ToDo> toDoArrayList = toDoList.getToDoArrayList();
            ToDo toDo = toDoService.findOne(email, workRequestDto.getTodoName());


            //새로운 work 생성 및 workarraylist에 추가
            Work work = new Work(workRequestDto);
            log.info("todo name :"+toDo.getName());
            if (toDo.getWorkArrayList() == null) {
                toDo.setWorkArrayList(new ArrayList<>());
                ArrayList<Work> workArrayList = toDo.getWorkArrayList();
                workArrayList.add(work);
            } else {
                ArrayList<Work> workArrayList = toDo.getWorkArrayList();
                workArrayList.add(work);
            }


            Iterator<ToDo> it = toDoArrayList.iterator();
            while(it.hasNext()){
                ToDo element = it.next();
                if(element.getName().equals(workRequestDto.getTodoName())){
                    element.setWorkArrayList(toDo.getWorkArrayList());
                    break;
                }
            }

            toDoListRepository.deleteByEmail(email);
            toDoListRepository.save(new ToDoList(email,toDoArrayList));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Override
//    public ArrayList<Work> findWork(String email, String todoName){
//        try{
//            ToDo toDo = toDoService.findOne(email, todoName);
//            return toDo.getWorkArrayList();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    //todo 찾기. 원래 있던거 지우기. 추가하기.
    @Override
    @Transactional
    public void update(String email, WorkUpdateDto workUpdateDto){
        try{
            ArrayList<ToDo> toDoList = toDoListRepository.findByEmail(email).get().getToDoArrayList();
            ToDo toDo = toDoService.findOne(email, workUpdateDto.getTodoName());//work를 넣을 todo를 찾음
            ArrayList<Work> workArrayList = toDo.getWorkArrayList();
            Iterator<Work> it = workArrayList.iterator();
            log.info(String.valueOf(workArrayList.get(0)));

            //work save
            while(it.hasNext()){
                Work element = it.next();
                if (element.getName().equals(workUpdateDto.getOldName())) {
                    element.setName(workUpdateDto.getNewName());
                    element.setMemo(workUpdateDto.getMemo());
                    break;
                }
            }

            //todo save
            toDo.setWorkArrayList(workArrayList);
            Iterator<ToDo> todo_it = toDoList.iterator();
            while (todo_it.hasNext()) {
                ToDo toDo1 = todo_it.next();
                if (toDo1.getName().equals(workUpdateDto.getTodoName())) {
                    toDo1.setWorkArrayList(workArrayList);
                    break;
                }
            }

            toDoListRepository.save(new ToDoList(email, toDoList));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //todoname으로 todo찾기 workarraylist iterator로 접근, workname으로 찾기. 삭제하기. todo set workarraylist
    //repository.save(todolist): todolist의 todoarraylist에서 todoname으로 찾아서 원래있던거 지우고 새로만든거 넣기. repository save하기
    @Override
    public void delete(String email, WorkCDDto workDeleteDto){
        try{
            ArrayList<ToDo> toDoList = toDoListRepository.findByEmail(email).get().getToDoArrayList();
            ToDo toDo = toDoService.findOne(email, workDeleteDto.getTodoName());//work를 넣을 todo를 찾음
            ArrayList<Work> workArrayList = toDo.getWorkArrayList();


            Iterator<Work> it = workArrayList.iterator();
            //work save
            while(it.hasNext()){
                if (it.next().getName().equals(workDeleteDto.getWorkName())) {
                    it.remove();
                    break;
                }
            }

            //todo save
            Iterator<ToDo> todo_it = toDoList.iterator();
            while (todo_it.hasNext()) {
                ToDo toDo1 = todo_it.next();
                if (toDo1.getName().equals(workDeleteDto.getTodoName())) {
                    toDo1.setWorkArrayList(workArrayList);
                    break;
                }
            }

            toDoListRepository.save(new ToDoList(email, toDoList));

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    //todoName으로 todo찾기, 거기서 workName으로 work찾기(findWork)
    //work의 complete 변경하기
    //todo의 workarraylist에서 기존의 work 지우고 변경된거 추가하기
    //todoarraylist에서 기존의 todo 지우고 변경된거 추가하기
    //todolist save하기
    @Override
    public void complete(String email, WorkCDDto workCDDto){
        String todoName = workCDDto.getTodoName();
        String workName = workCDDto.getWorkName();

        ArrayList<ToDo> toDoList = toDoListRepository.findByEmail(email).get().getToDoArrayList();
        ToDo toDo = toDoService.findOne(email, workCDDto.getTodoName());
        ArrayList<Work> workArrayList = toDo.getWorkArrayList();


        Iterator<Work> it = workArrayList.iterator();
        while(it.hasNext()){
            Work element = it.next();
            if(element.getName().equals(workName)){
                if(element.isComplete()){
                    element.setComplete(false);
                } else{
                    element.setComplete(true);
                }
                break;
            }
        }

        Iterator<ToDo> todo_it = toDoList.iterator();
        while (todo_it.hasNext()) {
            ToDo toDo1 = todo_it.next();
            if (toDo1.getName().equals(todoName)) {
                toDo1.setWorkArrayList(workArrayList);
                break;
            }
        }

        toDoListRepository.deleteByEmail(email);
        toDoListRepository.save(new ToDoList(email, toDoList));
    }
}
