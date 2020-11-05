package com.mr.api;


import com.mr.pojo.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "category")
public interface CategoryApi {
    //查询
    @GetMapping(value = "list")
    List<Category> queryCategoryList(@RequestParam(value = "pid", defaultValue = "0") Long pid);

    //删除
    @DeleteMapping(value = "remove")
    void removeCategory(@RequestParam(value = "id") Long id);

    //修改回显2

    @GetMapping(value = "findById")
    Category findById(@RequestParam("id") Long id);

    //修改
    @PutMapping(value = "save")
    Category editCategory(@RequestBody Category category);

    //增加
    @PostMapping(value = "save")
    Category saveCategory(@RequestBody Category category);

    //查询品牌下拥有的分类

    /**
     * 通过品牌id查询分类数据
     */
    @GetMapping(value = "queryCategoryBrand/{bid}")
    List<Category> queryCategoryBrand(@PathVariable("bid") Long bid);

    //用于es库,根据分类ids,查询多个分类
    @GetMapping(value = "queryCategoryListByIds")
    List<Category> queryCategoryList(@RequestParam("ids") List<Long> ids);
}
