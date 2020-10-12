package com.jenkins.service.Impl;

import com.github.pagehelper.PageHelper;
import com.jenkins.dao.UserMapper;
import com.jenkins.entity.User;
import com.jenkins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User select(Long id) {
        PageHelper.startPage(1,10);
        return userMapper.selectByPrimaryKey(id);
    }
}
