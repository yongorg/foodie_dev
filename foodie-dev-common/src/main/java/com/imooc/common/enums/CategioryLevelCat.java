package com.imooc.common.enums;

/**
 * @Desc: 性别 枚举
 */
public enum CategioryLevelCat{

    FIRST(1,"一级分类"),
    SECOND(2,"二级分类"),
    THIRD(3,"三级分类");

    public final Integer type;
    public final String value;

    CategioryLevelCat(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
