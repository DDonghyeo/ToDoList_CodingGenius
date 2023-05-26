package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.Work;

import java.util.ArrayList;

public interface WorkService {

    public ArrayList<Work> findWork(String email, String todoName);
}
