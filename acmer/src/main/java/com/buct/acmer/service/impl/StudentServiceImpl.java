package com.buct.acmer.service.impl;

import com.buct.acmer.entity.Student;
import com.buct.acmer.mapper.StudentMapper;
import com.buct.acmer.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

//    @Resource
//    private StudentMapper studentMapper;
//
//    @Override
//    public List<Student> searchByName(String keyword) {
//        return studentMapper.searchByName(keyword);
//    }
}
