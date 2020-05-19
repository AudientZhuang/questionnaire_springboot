package com.zqu.yiban.questionnaire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqu.yiban.questionnaire.entity.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper extends BaseMapper<Teacher> {
    Teacher  queryClassTeacherBySnoAndTno(@Param("sNo") String sNo, @Param("tNo") String tNo);
    Teacher  queryInsitituteTeacherBySnoAndTno(@Param("sNo") String sNo, @Param("tNo") String tNo);
    Teacher  queryDomitoryTeacherBySnoAndTno(@Param("sNo") String sNo, @Param("tNo") String tNo);
}
