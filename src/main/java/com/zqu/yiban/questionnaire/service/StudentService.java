package com.zqu.yiban.questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.entity.Teacher;

import java.util.List;

public interface StudentService extends IService<Student> {
    public Teacher getClassTeacher(String s_no);

    public List<Teacher> getInstituteTeacher(String s_no);

    public List<Teacher> getDormitoryTeacher(String s_no);

    public Student login(String s_no, String s_password);

    public Boolean changePwd(String s_no, String newPassword);
}
