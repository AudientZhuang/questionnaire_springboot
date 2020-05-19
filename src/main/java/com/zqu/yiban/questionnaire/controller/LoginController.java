package com.zqu.yiban.questionnaire.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.service.StudentService;
import com.zqu.yiban.questionnaire.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
//@RequestMapping("/user")
public class LoginController {

    @Autowired
    StudentService studentService;

    /**
     * 学生登陆
     * @param map
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestParam Map<String, String> map, HttpServletRequest httpServletRequest) {

        String account = map.get("username");   //学号
        String password = map.get("password");  //密码

        if (account == null || password == null || "".equals(account) || "".equals(password)) { //判断前端发送的学号密码是否为空
            return new Result(ResultCode.NOT_ALLOW_SNO_PASSWORD_BLANK.getStatus(), ResultCode.NOT_ALLOW_SNO_PASSWORD_BLANK.getMessage(), null);
        }

        try {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();  //查询构造器
            queryWrapper.eq("s_no",account);    //查询条件：学号
            queryWrapper.eq("s_password",EncryptUtils.getEncryption(password)); //查询条件：密码
            Student student = studentService.getOne(queryWrapper);  //通过学号密码查询学生信息

            if (student != null) {  //查询得到学生
                httpServletRequest.getSession().setAttribute("s_no", account);  //将学号加到Session中
                httpServletRequest.getSession().setAttribute("s_name",student.getSName());  //将学生姓名加到Session中
                log.info("学号为：" + student.getSNo() + "的学生登陆");
                student.setSPassword(null); //密码设置为空，出于安全考虑不传密码给前端
                return new Result(ResultCode.LOGIN_SUCCESS.getStatus(), ResultCode.LOGIN_SUCCESS.getMessage(), student);
            }else{  //查询不到学生
                log.info("学号为：" + account + ",密码为：" + password + "的学生登陆失败");
                return new Result(ResultCode.ERROR_PASSWORD.getStatus(), ResultCode.ERROR_PASSWORD.getMessage(),null);
            }
        } catch (Exception e) { //捕获异常
            e.printStackTrace();
            log.info("发生异常：" + e.getMessage());
            return new Result(ResultCode.UNKNOW_ERROR_HAPPEN.getStatus(), ResultCode.UNKNOW_ERROR_HAPPEN.getMessage(), null);
        }
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @PutMapping(value = "/change")
    public Result changePassword(@RequestParam Map<String, String> map) {

        String s_no = map.get("username");  //获取学号
        String oldPassword = map.get("oldPassword");    //获取旧密码
        String newPassword = map.get("newPassword");    //获取新密码
        String sureNewPassword = map.get("sureNewPassword");    //获取确认密码

        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword) || sureNewPassword == null || "".equals(sureNewPassword)) {
            //旧密码、新密码、确认密码都不能为空
            return new Result(ResultCode.NOT_ALLOW_OLD_NEW_PASSWORD_BLANK.getStatus(), ResultCode.NOT_ALLOW_OLD_NEW_PASSWORD_BLANK.getMessage(), null);
        }

        if (!newPassword.equals(sureNewPassword)) { //新密码和确认密码不一致
            return new Result(ResultCode.OLD_NEW_PASSWORD_NOT_MATCH.getStatus(), ResultCode.OLD_NEW_PASSWORD_NOT_MATCH.getMessage(), null);
        }
        try {
            Student student = new Student();    //修改的学生实体
            student.setSPassword(newPassword);  //密码
            UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();   //更新构造器
            updateWrapper.eq("s_no",s_no);  //条件：学号
            updateWrapper.eq("s_password",oldPassword); //条件：密码
            boolean isUpdate = studentService.update(student, updateWrapper);   //修改密码

            if (isUpdate == true){  //修改成功
                log.info("学号为： " + s_no + "的学生修改密码成功");
                return new Result(ResultCode.ALTER_PSD_SUCCESS.getStatus(), ResultCode.ALTER_PSD_SUCCESS.getMessage(), null);
            }else { //修改失败
                log.info("学号为： " + s_no + "的学生修改密码失败");
                return new Result(ResultCode.ERROR_OLD_PASSWORD.getStatus(), ResultCode.ERROR_OLD_PASSWORD.getMessage(),null);
            }

        } catch (Exception e) { //捕获异常
            e.printStackTrace();
            log.info("发生异常：" + e.getMessage());
            return new Result(ResultCode.UNKNOW_ERROR_HAPPEN.getStatus(), ResultCode.UNKNOW_ERROR_HAPPEN.getMessage(), null);

        }
    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        try {
            if (session != null) {
                session.removeAttribute("s_no");
                session.removeAttribute("s_name");
                session.invalidate();
            }
            return new Result(ResultCode.LOG_OUT_SUCCESS.getStatus(), ResultCode.LOG_OUT_SUCCESS.getMessage(), null);
        } catch (Exception e) {
            return new Result(ResultCode.UNKNOW_ERROR_HAPPEN.getStatus(), ResultCode.UNKNOW_ERROR_HAPPEN.getMessage(), null);
        }

    }

}