package com.imooc.controller;

import com.imooc.common.utils.JSONResult;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author yongz
 * @Date 2020/12/18、16:10
 */
@RestController
@Slf4j
public class HelloController {
    @Autowired
    private StuService stuService;

    @GetMapping("/test/{id}")
    public Stu hello(@PathVariable Integer id) {
        return stuService.getStuService(id);
    }


    @GetMapping("/test/setSession")
    public Object setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 设置session  Attribute
        session.setAttribute("userInfo", "new user");
        // 设置过期时间， 0表示永不过时的
        session.setMaxInactiveInterval(3600);
        // 获取session  Attribute
        session.getAttribute("userInfo");
        // 移除session  Attribute
        // session.removeAttribute("userInfo");
        return JSONResult.ok();
    }


    @GetMapping("/test/loginfo")
    public Object loginfo() {
        log.debug("debug-------------------");
        log.info("info-------------------");
        log.warn("warn-------------------");
        log.error("error-------------------");
        return JSONResult.ok();
    }
}
