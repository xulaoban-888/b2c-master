package com.mr.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    private Long id;
    private Long cid;
    private String name;

    @Transient //一个规格组有多个规格参数,该字段与数据库无关,需要忽略
    private List<SpecParam> specParamList;
}
