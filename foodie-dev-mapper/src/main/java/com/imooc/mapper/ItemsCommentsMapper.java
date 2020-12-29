package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.ItemsComments;
import com.imooc.pojo.vo.CommentLevelCountsVO2;
import com.imooc.pojo.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapper extends MyMapper<ItemsComments> {
    List<CommentLevelCountsVO2> levelCounts(String itemId);
    List<ItemCommentVO> queryItemComments(@Param("map") Map<String, Object> map);
}