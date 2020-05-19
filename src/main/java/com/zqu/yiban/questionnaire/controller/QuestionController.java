package com.zqu.yiban.questionnaire.controller;

import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.entity.Question;
import com.zqu.yiban.questionnaire.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 问题控制器
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    /**
     * 获取班主任的问卷
     * @return
     */
    @GetMapping("/classTeacher")
    public Result getClassTeacherQuestion(){
        try {
            List<Question> classTeacherQuestionList = questionService.getClassTeacherQuestion();
            return new Result(ResultCode.CLASS_TEACHER_QUESTION_FOUND.getStatus(),ResultCode.CLASS_TEACHER_QUESTION_FOUND.getMessage(),classTeacherQuestionList);
        } catch (Exception e) { //捕获异常
            e.printStackTrace();
            return new Result(ResultCode.CLASS_TEACHER_QUESTION_NOT_FOUND.getStatus(),ResultCode.CLASS_TEACHER_QUESTION_NOT_FOUND.getMessage(),null);
        }
    }

    /**
     * 获取辅导员的问卷
     * @return
     */
    @GetMapping("/counselor")
    public Result getCounselorQuestion(){
        try {
            List<Question> counselorQuestionList = questionService.getCounselorQuestion();
            return new Result(ResultCode.COUNSELOR_QUESTION_FOUND.getStatus(),ResultCode.COUNSELOR_QUESTION_FOUND.getMessage(),counselorQuestionList);
        } catch (Exception e) { //捕获异常
            e.printStackTrace();
            return new Result(ResultCode.COUNSELOR_QUESTION_NOT_FOUND.getStatus(),ResultCode.COUNSELOR_QUESTION_NOT_FOUND.getMessage(),null);
        }
    }
}
