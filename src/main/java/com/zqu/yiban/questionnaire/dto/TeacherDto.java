package com.zqu.yiban.questionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto implements Serializable {
    String t_no;
    String t_name;
    int t_type;
    boolean t_flag;
}