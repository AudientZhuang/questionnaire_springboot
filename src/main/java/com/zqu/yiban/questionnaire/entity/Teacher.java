package com.zqu.yiban.questionnaire.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 老师
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "teacher")
public class Teacher {
    String t_no;   //老师工号
    String t_name;  //老师姓名
}
