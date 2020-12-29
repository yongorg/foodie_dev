package com.imooc.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    // 三级分类vo list
    private List<SubCategoryVO> subCatList;
}