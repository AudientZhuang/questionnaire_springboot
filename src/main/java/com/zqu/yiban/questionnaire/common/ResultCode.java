package com.zqu.yiban.questionnaire.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode {

    UNKNOW_ERROR_HAPPEN(-3, "未知错误发生"),

    STUDENT_NOT_FOUND(-2, "用户未找到"),

    NOT_ALLOW_OLD_NEW_PASSWORD_BLANK(-2, "不允许新旧密码为空"),

    NOT_ALLOW_SNO_PASSWORD_BLANK(-1, "不允许账号密码为空"),

    OLD_NEW_PASSWORD_NOT_MATCH(-1, "新密码匹配不一致"),

    ERROR_PASSWORD(0, "输入的密码错误"),

    ERROR_OLD_PASSWORD(0, "输入的旧密码错误"),

    ALTER_PSD_SUCCESS(1, "修改成功"),

    LOGIN_SUCCESS(1, "登录成功"),

    TEACHER_NOT_FOUND(-1, "查询对应的老师失败"),

    TEACHER_FOUND(1, "查询对应的老师成功"),

    CLASS_TEACHER_QUESTION_NOT_FOUND(-1, "查询班主任问卷失败"),

    CLASS_TEACHER_QUESTION_FOUND(1, "查询班主任问卷成功"),

    COUNSELOR_QUESTION_NOT_FOUND(-1, "查询辅导员问卷失败"),

    COUNSELOR_QUESTION_FOUND(1, "查询辅导员问卷成功"),

    SUBMIT_RECORD_FAIL(-1, "提交失败"),

    SUBMIT_RECORD_SUCCESS(1, "提交成功"),

    SUBMIT_RECORD_FAIL_REPEAT(0, "重复提交"),

    LOG_OUT_SUCCESS(1, "注销成功");

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
