package com.imooc.pojo;

import javax.persistence.*;
import lombok.Data;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    @Id
    private Integer id;

    private String name;

    private Integer age;

}