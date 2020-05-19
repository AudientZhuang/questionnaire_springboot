package com.zqu.yiban.questionnaire.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.entity.Teacher;
import com.zqu.yiban.questionnaire.mapper.StudentMapper;
import com.zqu.yiban.questionnaire.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

}
