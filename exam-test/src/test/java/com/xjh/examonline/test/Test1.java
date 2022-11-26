package com.xjh.examonline.test;

import com.xjh.examonline.domain.Teacher;
import com.xjh.examonline.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 初始化教师信息
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class Test1 {

    @Autowired
    private TeacherService teacherService;


    @Test
    public void testOne(){
//        System.out.println("测试");
        Teacher teacher = new Teacher();
        teacher.setTname("张珊");
        teacher.setPass("123");

        teacherService.save(teacher);

    }
}
