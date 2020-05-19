package com.zqu.yiban.questionnaire.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student {
    String sNo;   //学生学号
    String sName;  //学生姓名
    String sPassword;  //学生密码
    String sClass;    //班级
    String sInstitute;    //学院
    String dName;  //宿舍名
}
