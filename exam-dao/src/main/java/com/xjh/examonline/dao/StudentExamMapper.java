package com.xjh.examonline.dao;

import com.xjh.examonline.domain.StudentExam;
import org.apache.ibatis.annotations.Param;

public interface StudentExamMapper {
    int deleteByPrimaryKey(@Param("examId") Long examId, @Param("studentId") Long studentId);

    int insert(StudentExam record);

    int insertSelective(StudentExam record);

    StudentExam selectByPrimaryKey(@Param("examId") Long examId, @Param("studentId") Long studentId);

    int updateByPrimaryKeySelective(StudentExam record);

    int updateByPrimaryKey(StudentExam record);
}