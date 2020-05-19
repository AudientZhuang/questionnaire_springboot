package com.zqu.yiban.questionnaire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

}
