package com.zqu.yiban.questionnaire.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 问题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "question")
public class Question implements Serializable {
    int q_no;   //问题编号
    String q_content;   //问题内容
    int q_type; //问题类型
}
