package com.zqu.yiban.questionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndTeacherDto implements Serializable {

    private String username;

    private List<TeacherDto> teachers;
}
