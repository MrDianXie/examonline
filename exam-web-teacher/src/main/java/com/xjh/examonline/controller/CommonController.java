package com.xjh.examonline.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.xjh.examonline.domain.Teacher;
import com.xjh.examonline.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * 提供基本功能
 * */
@Controller
//@SessionAttributes("loginTeacher")
public class CommonController {

    Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private TeacherService teacherService;


    @RequestMapping({"/common/login.html","/"})
    public String toLogin(){
        return "common/login";
    }

    @RequestMapping("/common/main.html")
    public String toMain(){
        return "common/main";
    }


    @RequestMapping("common/login")
    @ResponseBody
    public boolean checkLogin(String tname, String tpass, HttpSession session){
        log.info(tname,"tname");
        log.info(tpass,"tpass");

        //根据老师用户名寻找用户信息
        Teacher teacher = teacherService.findByName(tname);
        if (teacher == null){
            log.info("登录失败","用户名不正确");
            return false;
        }

        //在判断密码是否正确
        tpass = DigestUtil.md5Hex(tpass);
        if (!teacher.getPass().equals(tpass)){
            log.info("登录失败","密码错误");
            return false;
        }

        log.info("登录成功");
        session.setAttribute("loginTeacher",teacher);
        return true;
    }

    @RequestMapping("common/exit")
    public String exit(HttpSession session){
//        清空session
//        session.invalidate();
        session.removeAttribute("loginTeacher");
        return "common/login";
    }

    @RequestMapping("/common/timeout.html")
    public String toTimeout(){
        return "/common/timeout";
    }

    //设计一个方法用于请求修改密码页面
    @RequestMapping("common/updatePwdTemplate.html")
    public String toUpdatePwd(){
        return "common/updatePwdTemplate";
    }

    //设计一个方法用来修改密码
    @RequestMapping("common/updatePwd")
    @ResponseBody
    public boolean updatePwd(String old_pass,String new_pass,HttpSession session){
        //从session中获取老师对象
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        //将传入的原密码加密
        String old_pass_md = DigestUtil.md5Hex(old_pass);
        //从老师对象中获取密码与传入的原密码参数比较
        if (old_pass_md.equals(loginTeacher.getPass())){
            //如果相等证明原密码正确
            //将新密码存入数据库中
            //获取当前教师用户的id
            Long id = loginTeacher.getId();
            int i = teacherService.updatePwd(id, new_pass);
            if (i == 1){
                log.debug("modify successfully:[{}]",new_pass);
                return true;
            } else {
                log.debug("modify fail:[{}]",new_pass);
                return false;
            }

        } else {
            //原密码不正确
            log.debug("old_pass not find:[{}]",old_pass);
            return false;
        }

    }
}
