package com.zqu.yiban.questionnaire.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.dto.StudentAndTeacherDto;
import com.zqu.yiban.questionnaire.dto.TeacherDto;
import com.zqu.yiban.questionnaire.entity.Record;
import com.zqu.yiban.questionnaire.entity.Teacher;
import com.zqu.yiban.questionnaire.service.RecordService;
import com.zqu.yiban.questionnaire.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RecordService recordService;

    /**
     * 获取当前学生的所有老师信息
     * @param request
     * @return
     */
    @GetMapping("/teachers")
    public Result getAllTeacher(HttpServletRequest request){

        try{

            String s_no = null;
            s_no = request.getSession().getAttribute("s_no").toString();    //获取学生的学号
            String username = request.getSession().getAttribute("s_name").toString();   //获取学生的姓名
            if(s_no == null){   //获取不到学号
                throw new Exception("session已过期");
            }

            List<TeacherDto> teacherList = new ArrayList<>();   //老师集合,用于存放当前学生对应的老师

            /**
             * 获取学生对应的班主任信息
             */
            Teacher classTeacher = studentService.getClassTeacher(s_no);    //获取班主任
            log.info("班主任：{工号：" + classTeacher.getTNo() + ",姓名：" + classTeacher.getTName() + "}");

            if(classTeacher != null){   //找到该学生的班主任信息
                log.info("班主任：{工号：" + classTeacher.getTNo() + ",姓名：" + classTeacher.getTName() + "}");

                TeacherDto classTeacherDto = TeacherDto.toTeacherDto(classTeacher); //将从数据库查找的班主任信息封装为前端需要的老师对象TeacherDto

                //查询该学生对班主任的评教记录
                Record record = recordService.getOne(new QueryWrapper<Record>().eq("s_no",s_no)
                        .eq("t_no",classTeacher.getTNo()).eq("t_type",1));
                if(record == null){ //该学生未对班主任进行评教
                    classTeacherDto.setT_flag(false);   //false表示未评教
                }else{  //该学生已对班主任进行评教
                    classTeacherDto.setT_flag(true);    //true表示已评教
                }
                classTeacherDto.setT_type(1);   //教师类别，1表示班主任
                teacherList.add(classTeacherDto);   //加入到返回到前端的教师集合中

            }

            /**
             * 获取学生对应的学院辅导员信息
             */
            List<Teacher> instituteTeacher = studentService.getInstituteTeacher(s_no);  //获取学院辅导员集合
            for (Teacher t: instituteTeacher) { //对集合进行遍历
                if(t != null){
                    TeacherDto instituteTeacherDto = TeacherDto.toTeacherDto(t);    //将从数据库查找的学院辅导员信息封装为前端需要的老师对象TeacherDto
                    Map<String,Object> conditionMap = new HashMap<>();  //查询条件
                    conditionMap.put("s_no",s_no);      //条件：学号
                    conditionMap.put("t_no",t.getTNo());//条件：老师工号
                    conditionMap.put("t_type",2);       //条件：老师类别，2表示学院辅导员
                    Record record = recordService.getOne(new QueryWrapper<Record>().allEq(conditionMap));   //查询该学生对当前学院辅导员的评教记录
                    if(record == null){ //该学生未对当前学院辅导员进行评教
                        instituteTeacherDto.setT_flag(false);   //false表示未评教
                    }else{      //该学生已对当前学院辅导员进行评教
                        instituteTeacherDto.setT_flag(true);    //true表示已评教
                    }
                    instituteTeacherDto.setT_type(2);   //教师类别，2表示辅导员
                    teacherList.add(instituteTeacherDto);   //加入到返回到前端的教师集合中

                }
            }

            /**
             * 获取学生对应的宿舍辅导员信息
             */
            List<Teacher> dormitoryTeacher = studentService.getDormitoryTeacher(s_no);  //获取宿舍辅导员集合
            for (Teacher t: dormitoryTeacher) {
                if(t != null){
                    TeacherDto dormitoryTeacherDto = TeacherDto.toTeacherDto(t);    //将从数据库查找的宿舍辅导员信息封装为前端需要的老师对象TeacherDto
                    Map<String,Object> conditionMap = new HashMap<>();  //查询条件
                    conditionMap.put("s_no",s_no);      //条件：学号
                    conditionMap.put("t_no",t.getTNo());//条件：老师工号
                    conditionMap.put("t_type",2);       //条件：老师类别，2表示学院辅导员
                    Record record = recordService.getOne(new QueryWrapper<Record>().allEq(conditionMap));//查询该学生对当前宿舍辅导员的评教记录
                    if(record == null){ //该学生未对当前学院辅导员进行评教
                        dormitoryTeacherDto.setT_flag(false);   //false表示未评教
                    }else{          //该学生已对当前学院辅导员进行评教
                        dormitoryTeacherDto.setT_flag(true);    //true表示已评教
                    }
                    dormitoryTeacherDto.setT_type(2);       //教师类别，2表示辅导员
                    teacherList.add(dormitoryTeacherDto);   //加入到返回到前端的教师集合中

                }
            }

            //封装返回数据
            StudentAndTeacherDto studentAndTeacherDto = new StudentAndTeacherDto();
            studentAndTeacherDto.setTeachers(teacherList);
            studentAndTeacherDto.setUsername(username);
            return new Result(ResultCode.TEACHER_FOUND.getStatus(),ResultCode.TEACHER_FOUND.getMessage(),studentAndTeacherDto);
        }catch (Exception e){   //捕获异常
            e.printStackTrace();
            return new Result(ResultCode.UNKNOW_ERROR_HAPPEN.getStatus(),ResultCode.UNKNOW_ERROR_HAPPEN.getMessage(),null);
        }

    }

}
