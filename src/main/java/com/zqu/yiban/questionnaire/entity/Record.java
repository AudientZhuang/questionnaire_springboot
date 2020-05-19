package com.zqu.yiban.questionnaire.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;
import java.io.Serializable;

/**
 * 记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "record")
public class Record implements Serializable {
    String s_no;   //学生学号
    String s_name;  //学生姓名
    String t_no;   //老师工号
    String t_name;  //老师姓名
    String q_answer;   //答案
    int t_type;     //老师类型
}
