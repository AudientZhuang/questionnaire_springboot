package com.zqu.yiban.questionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto implements Serializable {
    int q_no;   //问题编号
    String t_no;   //老师工号
    String t_name;  //老师姓名
    int q_answer;   //问卷答案
    int t_type; //老师类型
}
