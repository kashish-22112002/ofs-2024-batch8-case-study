package com.ofss.main.service;

import com.ofss.main.domain.LoginDetail;
import com.ofss.main.repository.LoginDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginDetailServiceImpl implements LoginDetailService {

    @Autowired
    private LoginDetailRepository loginDetailRepository;

    @Override
    public LoginDetail saveLoginDetail(LoginDetail loginDetail) {
        return loginDetailRepository.save(loginDetail);
    }

    @Override
    public LoginDetail getLoginDetailByUsername(String username) {
        // Implement this if you have a method in repository
        return loginDetailRepository.findByUsername(username);
    }

    @Override
    public boolean authenticate(String username, String password) {
        LoginDetail loginDetail = getLoginDetailByUsername(username);
        if (loginDetail != null) {
            if (loginDetail.getPassword().equals(password)) {
                loginDetail.setAttempts(0);
                loginDetailRepository.save(loginDetail);
                return true;
            } else {
                incrementLoginAttempts(username);
                if (loginDetail.getAttempts() >= 3) {
                    lockAccount(username);
                }
            }
        }
        return false;
    }

    @Override
    public void incrementLoginAttempts(String username) {
        LoginDetail loginDetail = getLoginDetailByUsername(username);
        if (loginDetail != null) {
            loginDetail.setAttempts(loginDetail.getAttempts() + 1);
            loginDetailRepository.save(loginDetail);
        }
    }

    @Override
    public void lockAccount(String username) {
        // Implement account locking logic if needed
        // For example, you could also update the CustomerDetail entity to set lockedStatus to true
    }
}
