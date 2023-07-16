package com.buct.acmer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buct.acmer.entity.User;
import com.buct.acmer.mapper.UserMapper;
import com.buct.acmer.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
