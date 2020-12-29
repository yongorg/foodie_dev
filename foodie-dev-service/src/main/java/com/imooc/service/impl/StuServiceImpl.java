package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yongz
 * @Date 2020/12/18、17:34
 */
@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Override
    public Stu getStuService(Integer id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public Stu updateStu(Integer id) {
        return null;
    }

    @Override
    public Stu deleteStu(Integer id) {
        return null;
    }

    @Override
    public Stu saveStu() {
        return null;
    }
}
