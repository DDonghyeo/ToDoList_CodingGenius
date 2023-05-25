package com.codingGenius.coding_genius.service;

public interface LoginService {
    public void requestEmailValidation(String email);

    public boolean checkEmailValidation(String email, String code);

    public void userRegister(String name, String email, String password);

    public boolean userLogin(String email, String password);
}
