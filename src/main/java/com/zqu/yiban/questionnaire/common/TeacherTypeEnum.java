package com.zqu.yiban.questionnaire.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum TeacherTypeEnum {
    //班主任类型
    CLASS_TEACHER(1),
    //辅导员类型
    COUNSEL_TEACHER(2);

    private final int type;
}
