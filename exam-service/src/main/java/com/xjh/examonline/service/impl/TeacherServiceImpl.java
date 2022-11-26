package com.xjh.examonline.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.xjh.examonline.dao.TeacherMapper;
import com.xjh.examonline.domain.Teacher;
import com.xjh.examonline.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    private Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public void save(Teacher teacher) {
        //生成教师名称对应的助记码-->hutool  生成助记码-->pinyin4j
        String mnemonicCode =  PinyinUtil.getPinyin(teacher.getTname(),"");
        teacher.setMnemonicCode(mnemonicCode);
        //验证教师名字与助记码是否重复 数据库唯一键可以实现，或逻辑实现，用给的教师名字和助记码去数据库里查
        //用dm5将密码加密后在存入数据库
        String pass = DigestUtil.md5Hex(teacher.getPass());
        teacher.setPass(pass);
        //引入dao

        try {
            teacherMapper.insert(teacher);
        } catch (DuplicateKeyException e){
            log.info("名称或助记码重复");
        }

    }

    @Override
    public Teacher findByName(String tname) {
        return teacherMapper.findByName(tname);
    }

    @Override
    public int updatePwd(Long id, String new_pass) {
        //将新密码加密
       new_pass = DigestUtil.md5Hex(new_pass);
       return teacherMapper.updatePwd(id,new_pass);
    }


}
