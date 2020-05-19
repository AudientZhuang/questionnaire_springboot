package com.zqu.yiban.questionnaire.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private int status; //状态码

    private String message; //返回信息

    private Object data;    //返回数据
}
