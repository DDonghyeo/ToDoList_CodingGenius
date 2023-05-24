package com.codingGenius.coding_genius.service;

public interface LoginService {
    public boolean requestEmailValidation(String email);

    public boolean checkEmailValidation(String email, String code)
}
