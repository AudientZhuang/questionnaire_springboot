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
    String s_no;   //学生学号
    String s_name;  //学生姓名
    String s_password;  //学生密码
    String s_class;    //班级
    String s_institute;    //学院
    String d_name;  //宿舍名
}
