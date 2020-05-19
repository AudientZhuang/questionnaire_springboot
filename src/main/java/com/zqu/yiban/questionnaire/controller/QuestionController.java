package com.zqu.yiban.questionnaire.controller;

import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.entity.Question;
import com.zqu.yiban.questionnaire.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 问题控制器
 */
@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 获取班主任的问卷
     * @return Result
     */
    @GetMapping("/classTeacher")
    public Result getClassTeacherQuestion(){
        return new Result(HttpStatus.OK.value(), "请求成功", questionService.queryClassTeacherQuestion());
    }

    /**
     * 获取辅导员的问卷
     * @return Result
     */
    @GetMapping("/counselor")
    public Result getCounselorQuestion(){
        return new Result(HttpStatus.OK.value(), "请求成功", questionService.gueryCounselorQuestion());
    }
}
