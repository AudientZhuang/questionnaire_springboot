package com.zqu.yiban.questionnaire.dto;

import com.zqu.yiban.questionnaire.entity.Teacher;
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

    public static TeacherDto toTeacherDto(Teacher teacher){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setT_no(teacher.getTNo());
        teacherDto.setT_name(teacher.getTName());
        return teacherDto;
    }
}