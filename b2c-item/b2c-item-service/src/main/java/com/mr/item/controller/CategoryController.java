package com.mr.item.controller;

import com.mr.common.enums.ExceptionEnums;
import com.mr.common.exception.MrException;
import com.mr.item.service.CategoryService;
import com.mr.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    //查询
    @GetMapping(value = "list")
    public ResponseEntity<List<Category>> queryCategoryList(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

        List<Category> list = this.service.queryByList(pid);
        if (list == null && list.size() == 0) {
            throw new MrException(ExceptionEnums.CATEGORY_CANNOT_BE_NULL);
        }
        //相当于 ResponseEntity.status(200).body(list)
        return ResponseEntity.ok(list);
    }

    //删除
    @DeleteMapping(value = "remove")
    public ResponseEntity removeCategory(@RequestParam(value = "id") Long id) {

        return this.service.remove(id);
    }

    //修改回显2

    @GetMapping(value = "findById")
    public ResponseEntity<Category> findById(@RequestParam("id") Long id) {
        return this.service.findById(id);
    }

    //修改
    @PutMapping(value = "save")
    public ResponseEntity<Category> editCategory(@RequestBody Category category) {
        return this.service.save(category);
    }

    //增加
    @PostMapping(value = "save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return this.service.save(category);
    }

    //查询品牌下拥有的分类

    /**
     * 通过品牌id查询分类数据(用于品牌新增,把品牌新增到分类下面)
     */
    @GetMapping(value = "queryCategoryBrand/{bid}")
    public ResponseEntity<List<Category>> queryCategoryBrand(@PathVariable("bid") Long bid) {
        List<Category> list = service.queryCategoryByBid(bid);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    //用于es库,根据分类ids,查询多个分类
    @GetMapping(value = "queryCategoryListByIds")
    public ResponseEntity<List<Category>> queryCategoryList(@RequestParam("ids") List<Long> ids) {
        if (ids == null || ids.equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(service.queryCategoryList(ids));
    }
}
