package com.imooc.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 6个最新商品的简单数据类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleItemVO {

    private String itemId;
    private String itemName;
    private String itemUrl;

}