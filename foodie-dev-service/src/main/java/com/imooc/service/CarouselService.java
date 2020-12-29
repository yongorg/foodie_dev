package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.security.PublicKey;
import java.util.List;

public interface CarouselService {

    /**
     * 查询所有轮播图列表
     */
    public List<Carousel> queryAll(Integer isShow);
}