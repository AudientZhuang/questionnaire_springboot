package com.zqu.yiban.questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqu.yiban.questionnaire.entity.Question;

import java.util.List;

public interface QuestionService extends IService<Question> {
    List<Question> queryClassTeacherQuestion();
    List<Question> gueryCounselorQuestion();
}
