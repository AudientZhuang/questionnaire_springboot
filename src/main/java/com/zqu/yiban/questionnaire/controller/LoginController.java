package com.zqu.yiban.questionnaire.controller;



import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.service.StudentService;
import com.zqu.yiban.questionnaire.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/user")
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
    public Result login(@RequestBody  Map<String,String> map, HttpServletRequest httpServletRequest) {

        String account = map.get("username");
        String password = map.get("password");
        //判断前端发送的学号密码是否为空
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return new Result(HttpStatus.UNAUTHORIZED.value(), "学号密码不能为空",null);
        }
        Student student = studentService.login(account,EncryptUtils.getEncryption(password));  //通过学号密码查询学生信息

        if (student != null) {  //查询得到学生
            httpServletRequest.getSession().setAttribute("s_no", account);  //将学号加到Session中
            httpServletRequest.getSession().setAttribute("s_name",student.getSName());  //将学生姓名加到Session中
            log.info("学号为：" + student.getSNo() + "的学生登陆");
            student.setSPassword(null); //密码设置为空，出于安全考虑不传密码给前端
            return new Result(HttpStatus.OK.value(), "请求成功", student);
        }else{  //查询不到学生
            log.info("学号为：" + account + ",密码为：" + password + "的学生登陆失败");
            return new Result(HttpStatus.UNAUTHORIZED.value(), "学号或者密码错误",null);
        }
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @PutMapping(value = "/changePsd")
    public Result changePassword(@RequestBody Map<String, String> map) throws Exception {

        String s_no = map.get("username");  //获取学号
        String oldPassword = map.get("oldPassword");    //获取旧密码
        String newPassword = map.get("newPassword");    //获取新密码
        String sureNewPassword = map.get("sureNewPassword");    //获取确认密码

        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(sureNewPassword)) {
            //旧密码、新密码、确认密码都不能为空
            return new Result(HttpStatus.UNAUTHORIZED.value(), "密码不能为空",null);
        }

        if (!newPassword.equals(sureNewPassword)) { //新密码和确认密码不一致
            return new Result(HttpStatus.UNAUTHORIZED.value(), "前后输入密码不一致",null);
        }

        Student student = studentService.login(s_no, EncryptUtils.getEncryption(oldPassword));

        if (student == null){
            return new Result(HttpStatus.UNAUTHORIZED.value(), "密码错误",null);
        }

        boolean isUpdate = studentService.changePwd(s_no, EncryptUtils.getEncryption(newPassword));  //修改密码

        if (isUpdate == true){  //修改成功
            log.info("学号为： " + s_no + "的学生修改密码成功");
            return new Result(HttpStatus.OK.value(), "请求成功", null);
        }else { //修改失败
            log.info("学号为： " + s_no + "的学生修改密码失败");
            throw new Exception("修改密码失败");
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
        if (session != null) {
            session.removeAttribute("s_no");
            session.removeAttribute("s_name");
            session.invalidate();
            return new Result(HttpStatus.OK.value(), "请求成功", null);
        } else {
            return new Result(HttpStatus.UNAUTHORIZED.value(), "不是登录状态", null);
        }
    }

}