package com.xjh.examonline.service;

import com.xjh.examonline.domain.Teacher;


public interface TeacherService {
    void save(Teacher teacher);

    Teacher findByName(String tname);

    int updatePwd(Long id,String new_pass);

}
