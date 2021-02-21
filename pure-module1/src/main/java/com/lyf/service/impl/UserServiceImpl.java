package com.lyf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.entity.User;
import com.lyf.mapper.UserMapper;
import com.lyf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Administrator
 * 2021/2/9 14:12 周二
 * description
 **/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService<User> {

}
