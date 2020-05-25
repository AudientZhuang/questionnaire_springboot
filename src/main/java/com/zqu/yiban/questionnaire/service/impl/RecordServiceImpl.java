package com.zqu.yiban.questionnaire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqu.yiban.questionnaire.common.TeacherTypeEnum;
import com.zqu.yiban.questionnaire.entity.Record;
import com.zqu.yiban.questionnaire.entity.Teacher;
import com.zqu.yiban.questionnaire.mapper.RecordMapper;
import com.zqu.yiban.questionnaire.service.RecordService;
import com.zqu.yiban.questionnaire.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    private TeacherService teacherService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean saveRecord(Record record) {
        // 判断record中的老师是否信息匹配
        if (record.getTType() == TeacherTypeEnum.CLASS_TEACHER.getType()) {
            if (!this.validateClassTeacher(record)) {
                return false;
            }
        } else if (record.getTType() == TeacherTypeEnum.COUNSEL_TEACHER.getType()){
            // 查询辅导员信息是否匹配
            if (!this.validateCounselTeacher(record)) {
                return false;
            }
        } else {
            return false;
        }

        QueryWrapper<Record> queryWrapper = new QueryWrapper<Record>().eq("s_no" , record.getSNo()).eq("t_no", record.getTNo()).eq("t_type", record.getTType());
        if (this.list(queryWrapper).size() > 0) {
            return false;
        }
        return save(record);
    }

    /**
     * 班主任信息是否正确
     * @param record 提交的记录
     * @return
     */
    private boolean validateClassTeacher(Record record) {
        Teacher teacher = teacherService.queryClassTeacherBySnoAndTno(record.getSNo(), record.getTNo());
        return teacher != null && teacher.getTName().equals(record.getTName());
    }
    /**
     * 辅导员信息是否正确
     * @param record 提交的记录
     * @return
     */
    private boolean validateCounselTeacher(Record record) {
        String sNo = record.getSNo();
        String tNo = record.getTNo();
        String tName = record.getTName();
        Teacher teacher = teacherService.queryInsitituteTeacherBySnoAndTno(sNo, tNo);
        if (teacher == null){
            teacher = teacherService.queryDomitoryTeacherBySnoAndTno(sNo, tNo);
        }
        return teacher != null && teacher.getTName().equals(record.getTName());
    }
}
