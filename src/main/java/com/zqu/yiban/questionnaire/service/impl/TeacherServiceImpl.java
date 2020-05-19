package com.zqu.yiban.questionnaire.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqu.yiban.questionnaire.entity.Teacher;
import com.zqu.yiban.questionnaire.mapper.TeacherMapper;
import com.zqu.yiban.questionnaire.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Teacher queryClassTeacherBySnoAndTno(String sNo, String tNo) {
        return this.baseMapper.queryClassTeacherBySnoAndTno(sNo, tNo);
    }

    @Override
    public Teacher queryInsitituteTeacherBySnoAndTno(String sNo, String tNo) {
        return this.baseMapper.queryInsitituteTeacherBySnoAndTno(sNo, tNo);
    }

    @Override
    public Teacher queryDomitoryTeacherBySnoAndTno(String sNo, String tNo) {
        return this.baseMapper.queryDomitoryTeacherBySnoAndTno(sNo, tNo);
    }
}