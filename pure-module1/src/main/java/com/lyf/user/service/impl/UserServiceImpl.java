package com.lyf.user.service.impl;

import com.lyf.user.entity.User;
import com.lyf.user.mapper.UserMapper;
import com.lyf.user.service.USERervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyf
 * @since 2021-02-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements USERervice {

}
