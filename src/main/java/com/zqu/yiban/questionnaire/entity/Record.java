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
    String sNo;   //学生学号
    String sName;  //学生姓名
    String tNo;   //老师工号
    String tName;  //老师姓名
    String qAnswer;   //答案
    int tType;     //老师类型
}
