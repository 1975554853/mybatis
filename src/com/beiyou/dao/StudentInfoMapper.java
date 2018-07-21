package com.beiyou.dao;

import com.beiyou.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentInfoMapper {
    List<Student> SelectAll();
    Student SelectSingle(@Param("id") Integer id);
    Integer deleteById(Integer id);
    Integer addStudent(@Param("name") String name,@Param("sex") String sex,@Param("seat") Integer seat,@Param("desc") String desc);
    List<Student> selectlimt(@Param("page") Integer page,@Param("limit") Integer limit);
    Integer selectnum();
}
