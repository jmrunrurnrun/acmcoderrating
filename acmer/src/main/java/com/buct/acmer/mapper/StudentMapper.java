package com.buct.acmer.mapper;

import com.buct.acmer.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    //    List<Student> searchByName(@Param("keyword")String keyword);
    @Select("SELECT * FROM student WHERE stu_name LIKE CONCAT('%', #{keyword}, '%')")
    List<Student> searchByName(@Param("keyword") String keyword);

    @Select("SELECT COUNT(CASE WHEN stu_class > #{begin} AND stu_class < #{end} THEN 1 END) FROM student")
    int count(@Param("begin") int begin, @Param("end") int end);

}
