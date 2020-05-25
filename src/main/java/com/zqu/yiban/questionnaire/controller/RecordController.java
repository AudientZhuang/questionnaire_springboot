package com.zqu.yiban.questionnaire.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.common.TeacherTypeEnum;
import com.zqu.yiban.questionnaire.dto.RecordDTO;
import com.zqu.yiban.questionnaire.entity.Record;
import com.zqu.yiban.questionnaire.service.QuestionService;
import com.zqu.yiban.questionnaire.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/record")
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private QuestionService questionService;

    /**
     * 判断请求评教老师类型是否合法
     * @param requestTeacherType 评教老师类型
     * @return 是否合法
     */
    private boolean validateRequestTeacherQuestionType(Integer requestTeacherType) {
        if (requestTeacherType == null) {
            return false;
        }
        for (TeacherTypeEnum teacherTypeEnum : TeacherTypeEnum.values()) {
            if(teacherTypeEnum.getType() == requestTeacherType) {
                return true;
            }
        }
        return false;
    }

    /**
     * 提交问卷
     * @return 操作结果
     */
    @PostMapping("/submit")
    public Result submitRecord(@RequestBody RecordDTO recordDTO, HttpServletRequest request) throws Exception {
        // 获取学生学号
        Object sNo = request.getSession().getAttribute("s_no");
        // 获取学生姓名
        Object sName = request.getSession().getAttribute("s_name");
        if (sNo == null || sName == null) {
            return new Result(HttpStatus.UNAUTHORIZED.value(), "登录信息已过期", null);
        }
        log.debug(recordDTO.toString());
        // 避免请求数据的学号跟session不对应
        String requestSNo = recordDTO.getSNo();
        if ((requestSNo == null || !sNo.toString().equals(requestSNo)) || recordDTO.getAnswer() == null
                || !validateRequestTeacherQuestionType(recordDTO.getTType())|| recordDTO.getAnswer().size() != questionService.countQuestionByTType(recordDTO.getTType())) {
            return new Result(HttpStatus.BAD_REQUEST.value(), "异常请求", null);
        }
        Record record = new Record();
        record.setSNo(recordDTO.getSNo());
        record.setSName(sName.toString());
        record.setTNo(recordDTO.getTNo());
        record.setTName(recordDTO.getTName());
        record.setTType(recordDTO.getTType());
        record.setQAnswer(new ObjectMapper().writeValueAsString(recordDTO.getAnswer()));
        boolean success = recordService.saveRecord(record);
        if (!success) {
            return new Result(HttpStatus.BAD_REQUEST.value(), "请求失败", null);
        }
        log.info("学号{}姓名{}评教{}老师{}成功",sNo,sName,recordDTO.getTNo(),recordDTO.getTName());
        return new Result(HttpStatus.OK.value(), "请求成功", null);
    }
}
