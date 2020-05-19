package com.zqu.yiban.questionnaire.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqu.yiban.questionnaire.entity.Question;
import com.zqu.yiban.questionnaire.mapper.QuestionMapper;
import com.zqu.yiban.questionnaire.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
