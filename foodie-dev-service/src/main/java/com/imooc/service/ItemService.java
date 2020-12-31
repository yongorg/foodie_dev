package com.imooc.service;

import com.imooc.common.utils.PagedGridResult;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ShopcartVO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     * 搜索商品列表
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searhItems(String keywords, String sort,
                                      Integer page, Integer pageSize);

    /**
     * 根据分类id搜索商品列表
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searhItems(Integer catId, String sort,
                                      Integer page, Integer pageSize);

    @Transactional(propagation = Propagation.SUPPORTS)
    List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据规格ids查询最新的购物车中商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds
     * @return
     */
   // public List<ShopcartVO> queryItemsBySpecIds(String specIds);

}
