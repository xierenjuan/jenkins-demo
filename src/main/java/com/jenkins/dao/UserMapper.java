package com.jenkins.dao;

import com.jenkins.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    User selectByPrimaryKey(Long id);


}