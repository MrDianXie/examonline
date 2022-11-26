package com.xjh.examonline.dao;

import com.xjh.examonline.domain.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher findByName(String tname);

    int updatePwd(@Param("id") Long id, @Param("new_pass") String new_pass);
}