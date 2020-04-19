package com.jenkins.service;


import com.jenkins.entity.User;

public interface UserService {

    User select(Long id);
}
