package com.imooc.service.impl;

import com.imooc.mapper.CategoryMapper;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yongz
 * @Date 2020/12/29、16:26
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findAllByType(Integer type) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria .andEqualTo("type" , type);
        return categoryMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapper.getSubCatList(rootCatId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);
        return categoryMapper.getSixNewItemsLazy(map);
    }
}
