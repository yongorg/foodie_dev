package com.imooc.service.impl;

import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Users;
import com.imooc.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author yongz
 * @Date 2020/12/29、14:26
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Carousel> queryAll(Integer isShow) {
        Example carouselExample = new Example(Carousel.class);
        Example.Criteria carouselCriteria = carouselExample.createCriteria();

        carouselCriteria.andEqualTo("isShow", isShow);
        return carouselMapper.selectByExample(carouselExample);
    }
}
