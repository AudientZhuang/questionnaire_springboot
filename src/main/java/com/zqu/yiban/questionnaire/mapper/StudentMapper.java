package com.zqu.yiban.questionnaire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    public Teacher getClassTeacher(@Param("s_no") String s_no) throws Exception;

    public List<Teacher> getInstituteTeacher(@Param("s_no") String s_no) throws Exception;

    public List<Teacher> getDormitoryTeacher(@Param("s_no") String s_no) throws Exception;




}
