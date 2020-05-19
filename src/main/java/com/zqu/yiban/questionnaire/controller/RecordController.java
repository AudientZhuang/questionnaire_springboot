package com.zqu.yiban.questionnaire.controller;

import com.zqu.yiban.questionnaire.common.Result;
import com.zqu.yiban.questionnaire.common.ResultCode;
import com.zqu.yiban.questionnaire.entity.Record;
import com.zqu.yiban.questionnaire.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    /**
     * 提交问卷
     * @return
     */
    @PostMapping("/record")
    public Result submitRecord(@RequestBody Record record, HttpServletRequest request){
        try{
            String s_no = null;
            String s_name = null;
            s_no = request.getSession().getAttribute("s_no").toString();
            s_name =request.getSession().getAttribute("s_name").toString();
            if(s_no == null){
                throw new Exception("session已过期");
            }

            Record oldRecord = recordService.selectRecordBySnoAndTno(s_no,record.getT_no(),record.getT_type());
            if(oldRecord != null){
                return new Result(ResultCode.SUBMIT_RECORD_FAIL_REPEAT.getStatus(),ResultCode.SUBMIT_RECORD_FAIL_REPEAT.getMessage(),null);
            }

            int result = recordService.insertRecord(record);
            if(result <= 0){
                throw new Exception("插入记录失败");
            }
            return new Result(ResultCode.SUBMIT_RECORD_SUCCESS.getStatus(),ResultCode.SUBMIT_RECORD_SUCCESS.getMessage(),null);
        }catch (Exception e){   //捕获异常
            e.printStackTrace();
            return new Result(ResultCode.SUBMIT_RECORD_FAIL.getStatus(),ResultCode.SUBMIT_RECORD_FAIL.getMessage(),null);
        }
    }
}
