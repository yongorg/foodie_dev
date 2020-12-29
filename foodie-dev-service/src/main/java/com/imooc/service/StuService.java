package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {

    Stu getStuService(Integer id);
    Stu updateStu(Integer id);
    Stu deleteStu(Integer id);
    Stu saveStu();
}
