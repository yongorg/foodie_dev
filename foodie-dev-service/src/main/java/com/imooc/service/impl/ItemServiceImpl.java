package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.common.enums.CommentLevel;
import com.imooc.common.utils.DesensitizationUtil;
import com.imooc.common.utils.PagedGridResult;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.CommentLevelCountsVO2;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author yongz
 * @Date 2020/12/29、14:26
 */
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;


    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items findItemById(String itemId) {

        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> findItemImgList(String itemId) {

        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> findItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam findItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    /**
     * @athor yongz 实现方式
     * @param itemId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public CommentLevelCountsVO queryCommentCounts2(String itemId) {
        List<CommentLevelCountsVO2> commentLevelCounts = itemsCommentsMapper.levelCounts(itemId);
        CommentLevelCountsVO CommentLevelCounts = CommentLevelCountsVO.builder().totalCounts(0).build();
        commentLevelCounts.forEach(
                commentLevel -> {
                    Integer counts = commentLevel.getCounts();
                    if (commentLevel.getCommentLevel() == CommentLevel.GOOD.type) {
                        CommentLevelCounts.setGoodCounts(counts);
                    } else if (commentLevel.getCommentLevel() == CommentLevel.NORMAL.type) {
                        CommentLevelCounts.setNormalCounts(counts);
                    } else if (commentLevel.getCommentLevel() == CommentLevel.BAD.type) {
                        CommentLevelCounts.setBadCounts(counts);
                    }
                    CommentLevelCounts.setTotalCounts(counts+CommentLevelCounts.getTotalCounts());
                }
        );

        return CommentLevelCounts;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts + normalCounts + badCounts;

        CommentLevelCountsVO countsVO = new CommentLevelCountsVO();
        countsVO.setTotalCounts(totalCounts);
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setBadCounts(badCounts);

        return countsVO;
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId, Integer level) {
        //        ItemsComments condition = new ItemsComments();
        //        condition.setItemId(itemId);
        //        if (level != null) {
        //            condition.setCommentLevel(level);
        //        }
        //        return itemsCommentsMapper.selectCount(condition);
        Example example = new Example(ItemsComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        criteria.andEqualTo("commentLevel",level);
        return itemsCommentsMapper.selectCountByExample(example);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId,
                                              Integer level,
                                              Integer page,
                                              Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);

        // mybatis-pagehelper

        /**
         * page: 第几页
         * pageSize: 每页显示条数
         */
        PageHelper.startPage(page, pageSize);

        List<ItemCommentVO> list = itemsCommentsMapper.queryItemComments(map);

        //log.info(list.toString());

        // 脱敏
        list.forEach(itemCommentVO ->
                itemCommentVO.setNickname(DesensitizationUtil.commonDisplay(itemCommentVO.getNickname())));


        return setterPagedGrid(list, page);
    }
    private PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
