package com.zqu.yiban.questionnaire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.entity.Teacher;
import com.zqu.yiban.questionnaire.mapper.StudentMapper;
import com.zqu.yiban.questionnaire.service.StudentService;
import com.zqu.yiban.questionnaire.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Teacher getClassTeacher(String s_no) {
        return studentMapper.getClassTeacher(s_no);
    }

    @Override
    public List<Teacher> getInstituteTeacher(String s_no) {
        return studentMapper.getInstituteTeacher(s_no);
    }

    @Override
    public List<Teacher> getDormitoryTeacher(String s_no) {
        return studentMapper.getDormitoryTeacher(s_no);
    }

    @Override
    public Student login(String s_no, String s_password) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();  //查询构造器
        queryWrapper.eq("s_no",s_no);    //查询条件：学号
        queryWrapper.eq("s_password", s_password); //查询条件：密码
        Student student = studentMapper.selectOne(queryWrapper);    //通过学号密码查询学生信息
        return student;
    }

    @Override
    public Boolean changePwd(String s_no, /*String oldPassword, */String newPassword) {
        Student student = new Student();    //修改的学生实体
        student.setSPassword(newPassword);  //密码
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();   //更新构造器
        updateWrapper.eq("s_no",s_no);  //条件：学号
        int isUpdate = studentMapper.update(student, updateWrapper);    //修改密码
        return isUpdate > 0 ? true : false;
    }
}
