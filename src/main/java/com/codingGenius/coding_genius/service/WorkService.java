package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.Work;
import com.codingGenius.coding_genius.dto.WorkCDDto;
import com.codingGenius.coding_genius.dto.WorkRequestDto;
import com.codingGenius.coding_genius.dto.WorkUpdateDto;

import java.util.ArrayList;

public interface WorkService {

    public void save(String email, WorkRequestDto workRequestDto);

    public ArrayList<Work> findWork(String email, String todoName);

    public void update(String email, WorkUpdateDto workUpdateDto);

    public void delete(String email, WorkCDDto workDeleteDto);

    public void complete(String email, WorkCDDto workCDDto);
}
