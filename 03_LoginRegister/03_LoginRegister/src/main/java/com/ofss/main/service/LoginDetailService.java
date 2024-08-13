package com.ofss.main.service;

import com.ofss.main.domain.LoginDetail;

public interface LoginDetailService {
    LoginDetail saveLoginDetail(LoginDetail loginDetail);
    LoginDetail getLoginDetailByUsername(String username);
    boolean authenticate(String username, String password);
    void incrementLoginAttempts(String username);
    void lockAccount(String username);
}
