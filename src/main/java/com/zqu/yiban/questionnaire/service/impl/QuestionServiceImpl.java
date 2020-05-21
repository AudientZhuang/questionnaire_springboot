package com.zqu.yiban.questionnaire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqu.yiban.questionnaire.common.TeacherTypeEnum;
import com.zqu.yiban.questionnaire.entity.Question;
import com.zqu.yiban.questionnaire.mapper.QuestionMapper;
import com.zqu.yiban.questionnaire.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public List<Question> queryClassTeacherQuestion() {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<Question>()
                .eq("q_type", TeacherTypeEnum.CLASS_TEACHER.getType());
        return this.list(queryWrapper);
    }

    @Override
    public List<Question> gueryCounselorQuestion() {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<Question>()
                .eq("q_type", TeacherTypeEnum.COUNSEL_TEACHER.getType());
        return this.list(queryWrapper);
    }

    @Override
    public int countQuestionByTType(Integer type) {
        QueryWrapper<Question>queryWrapper = new QueryWrapper<Question>().eq("q_type", type);
        return this.count(queryWrapper);
    }
}
