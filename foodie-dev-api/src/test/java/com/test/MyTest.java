package com.test;

import com.imooc.Application;
import com.imooc.pojo.Stu;
import com.imooc.service.CarouselService;
import com.imooc.service.StuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author yongz
 * @Date 2020/12/18、18:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MyTest {

    @Autowired
    private StuService stuService;

    @Autowired
    private CarouselService carouselService;
    @Test
    public void test() {
        Stu stuService = this.stuService.getStuService(1203);
        System.out.println(stuService);
    }

    @Test
    public void test1() {
        System.out.println(carouselService.queryAll(1));
    }

}
