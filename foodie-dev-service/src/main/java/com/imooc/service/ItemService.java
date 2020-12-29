package com.imooc.service;

import com.imooc.common.utils.PagedGridResult;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;

import java.util.List;
import java.util.Map;

public interface ItemService {


    /**
     * 根据商品Id查询详情
     *
     * @param itemId
     * @return
     */
    public Items findItemById(String itemId);


    /**
     * 根据商品Id查询商品图片列表
     *
     * @param itemId
     * @return
     */
    public List<ItemsImg> findItemImgList(String itemId);

    /**
     * 根据商品Id查询商品规格
     *
     * @param itemId
     * @return
     */
    public List<ItemsSpec> findItemSpecList(String itemId);

    /**
     * 根据商品Id查询商品参数
     *
     * @param itemId
     * @return
     */
    public ItemsParam findItemParam(String itemId);

    /**
     * 根据商品Id查询商品评价等级数量
     *
     * @param itemId
     * @return
     */
    public CommentLevelCountsVO queryCommentCounts(String itemId);

    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);
}
