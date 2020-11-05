package com.mr.api;


import com.mr.pojo.SpecGroup;
import com.mr.pojo.SpecParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "spec")
public interface SpecApi {
    //规格 查询
    @GetMapping(value = "groups/{cid}")
    List<SpecGroup> querySpecGroup(@PathVariable("cid") Long cid);

    //规格 增加
    @PostMapping(value = "group")
    void saveSpecGroup(@RequestBody SpecGroup specGroup);

    //规格 修改
    @PutMapping(value = "group")
    void updateSpecGroup(@RequestBody SpecGroup specGroup);

    //规格 删除
    @DeleteMapping
    void delete(@RequestParam("id") Long id);


    //规格 参数查询  cid
    //参数可通过category分类查询 :用于商品增加:点击分类出现品牌,给品牌赋规格组参数
    //searching:es搜索条件
    //特有规格generic用于前段查询商品详情
    @GetMapping("/params")
    List<SpecParam> querySpecParam(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic
    );

    //规格 参数增加
    @PostMapping(value = "param")
    void addSpecParam(@RequestBody SpecParam specParam);

    //规格 参数修改
    @PutMapping(value = "param")
    void editSpecParam(@RequestBody SpecParam specParam);

    //规格 参数删除
    @DeleteMapping(value = "param/{id}")
    void remove(@PathVariable("id") Long id);
}
