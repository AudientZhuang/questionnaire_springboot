package com.zqu.yiban.questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqu.yiban.questionnaire.entity.Record;

public interface RecordService extends IService<Record> {
    /**
     * 插入评教记录
     * @param record 记录信息
     * @return 是否执行成功
     */
    boolean saveRecord(Record record);
}
