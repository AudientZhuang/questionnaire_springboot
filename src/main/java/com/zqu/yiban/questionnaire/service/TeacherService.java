package com.zqu.yiban.questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqu.yiban.questionnaire.entity.Teacher;


public interface TeacherService extends IService<Teacher> {
    Teacher  queryClassTeacherBySnoAndTno(String sNo, String tNo);
    Teacher  queryInsitituteTeacherBySnoAndTno(String sNo,  String tNo);
    Teacher  queryDomitoryTeacherBySnoAndTno(String sNo, String tNo);
}
