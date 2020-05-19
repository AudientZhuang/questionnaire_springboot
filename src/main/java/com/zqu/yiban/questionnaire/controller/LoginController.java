package com.zqu.yiban.questionnaire.controller;


import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.entity.Student;
import com.zqu.yiban.questionnaire.service.StudentService;
import com.zqu.yiban.questionnaire.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    StudentService studentService;

    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String, String> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String account = map.get("username");
        String password = map.get("password");

        if (account == null || password == null || "".equals(account) || "".equals(password)) {
            return new Result(ResultCode.NOT_ALLOW_SNO_PASSWORD_BLANK.getStatus(), ResultCode.NOT_ALLOW_SNO_PASSWORD_BLANK.getMessage(), null);
        }

        try {

            Result result = studentService.login(account, EncryptUtils.getEncryption(password));

            if (result.getStatus() == ResultCode.LOGIN_SUCCESS.getStatus()) {
                httpServletRequest.getSession().setAttribute("s_no", account);
                httpServletRequest.getSession().setAttribute("s_name", ((Student) (result.getData())).getS_name());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.UNKNOW_ERROR_HAPPEN.getStatus(), ResultCode.UNKNOW_ERROR_HAPPEN.getMessage(), null);
        }
    }

    @PutMapping(value = "/change")
    public @ResponseBody
    Result changePassword(@RequestBody Map<String, String> map) {

        String s_no = map.get("username");
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        String sureNewPassword = map.get("sureNewPassword");

        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword) || sureNewPassword == null || "".equals(sureNewPassword)) {
            return new Result(ResultCode.NOT_ALLOW_OLD_NEW_PASSWORD_BLANK.getStatus(), ResultCode.NOT_ALLOW_OLD_NEW_PASSWORD_BLANK.getMessage(), null);
        }

        if (!newPassword.equals(sureNewPassword)) {
            return new Result(ResultCode.OLD_NEW_PASSWORD_NOT_MATCH.getStatus(), ResultCode.OLD_NEW_PASSWORD_NOT_MATCH.getMessage(), null);
        }
        try {

            return studentService.changePassword(s_no, EncryptUtils.getEncryption(oldPassword), EncryptUtils.getEncryption(newPassword));

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.NOT_ALLOW_OLD_NEW_PASSWORD_BLANK.getStatus(), ResultCode.NOT_ALLOW_OLD_NEW_PASSWORD_BLANK.getMessage(), null);

        }
    }

    @GetMapping("/logout")
    public @ResponseBody
    Result logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
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