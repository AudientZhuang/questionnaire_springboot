package com.zqu.yiban.questionnaire.controller;

import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.dto.StudentAndTeacherDto;
import com.zqu.yiban.questionnaire.dto.TeacherDto;
import com.zqu.yiban.questionnaire.entity.Record;
import com.zqu.yiban.questionnaire.entity.Teacher;
import com.zqu.yiban.questionnaire.service.RecordService;
import com.zqu.yiban.questionnaire.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RecordService recordService;

    /**
     * 获取当前学生的所有老师信息
     * @return  老师集合
     */
    @GetMapping("/teachers")
    public Result getAllTeacher(HttpServletRequest request){

        try{
            //获取学生的学号
            String s_no = null;
            s_no = request.getSession().getAttribute("s_no").toString();
            String username = (String) request.getSession().getAttribute("s_name");
            if(s_no == null){
                throw new Exception("session已过期");
            }
            //String s_no = "201724113159";
            //老师集合,用于存放当前学生对应的老师
            List<TeacherDto> teacherList = new ArrayList<>();
            //老师工号集合,用于避免老师出现重复的问题,一个老师可能既是班主任,又是辅导员
            //List<String> t_noList = new ArrayList<>();
            //获取班主任
            Teacher classTeacher = studentService.getClassTeacher(s_no);
            TeacherDto classTeacherDto = new TeacherDto();
            if(classTeacher != null){
                BeanUtils.copyProperties(classTeacher,classTeacherDto);
                Record record = recordService.selectRecordBySnoAndTno(s_no,classTeacher.getT_no(),1);
                if(record == null){
                    classTeacherDto.setT_flag(false);
                }else{
                    classTeacherDto.setT_flag(true);
                }
                classTeacherDto.setT_type(1);
                teacherList.add(classTeacherDto);
                //t_noList.add(classTeacher.getT_no());
            }
            //获取学院辅导员
            List<Teacher> instituteTeacher = studentService.getInstituteTeacher(s_no);
            for (Teacher t: instituteTeacher) {
                if(t != null){
                    /*if(t_noList.contains(t.getT_no())){
                        continue;
                    }else{*/
                    TeacherDto instituteTeacherDto = new TeacherDto();
                    BeanUtils.copyProperties(t,instituteTeacherDto);
                    Record record = recordService.selectRecordBySnoAndTno(s_no,t.getT_no(),2);
                    if(record == null){
                        instituteTeacherDto.setT_flag(false);
                    }else{
                        instituteTeacherDto.setT_flag(true);
                    }
                    instituteTeacherDto.setT_type(2);
                    teacherList.add(instituteTeacherDto);
                    // t_noList.add(t.getT_no());
                    //}
                }
            }
            //获取宿舍辅导员
            List<Teacher> dormitoryTeacher = studentService.getDormitoryTeacher(s_no);
            for (Teacher t: dormitoryTeacher) {
                if(t != null){
                   /* if(t_noList.contains(t.getT_no())){
                        continue;
                    }else{*/
                    TeacherDto dormitoryTeacherDto = new TeacherDto();
                    BeanUtils.copyProperties(t,dormitoryTeacherDto);
                    Record record = recordService.selectRecordBySnoAndTno(s_no,t.getT_no(),2);
                    if(record == null){
                        dormitoryTeacherDto.setT_flag(false);
                    }else{
                        dormitoryTeacherDto.setT_flag(true);
                    }
                    dormitoryTeacherDto.setT_type(2);
                    teacherList.add(dormitoryTeacherDto);
                    //t_noList.add(t.getT_no());
                    //}
                }
            }
            StudentAndTeacherDto studentAndTeacherDto = new StudentAndTeacherDto();
            studentAndTeacherDto.setTeachers(teacherList);
            studentAndTeacherDto.setUsername(username);
            return new Result(ResultCode.TEACHER_FOUND.getStatus(),ResultCode.TEACHER_FOUND.getMessage(),studentAndTeacherDto);
        }catch (Exception e){   //捕获异常
            e.printStackTrace();
            return new Result(ResultCode.TEACHER_NOT_FOUND.getStatus(),ResultCode.TEACHER_NOT_FOUND.getMessage(),null);
        }

    }

}
