package com.buct.acmer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buct.acmer.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
