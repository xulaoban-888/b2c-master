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
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity queryByParentId(@RequestParam("pid") Long pid) {

        List<Category> list = categoryService.queryListByParent(pid);
        if (list == null || list.size() == 0) {
            throw new MrException(ExceptionEnums.CATEGORY_CANNOT_BE_NULL);
        }
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("delete")
    public void deleteCategory(Long id) {

        categoryService.deleteCategory(id);
    }

    @PostMapping("save")
    public ResponseEntity<Void> addCategory(@RequestBody Category category) {

        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    //修改回显

    @GetMapping(value = "findById")
    public ResponseEntity<Category> findById(@RequestParam("id") Long id) {
        return this.categoryService.findById(id);
    }

    //修改
    @PutMapping(value = "save")
    public ResponseEntity<Category> editCategory(@RequestBody Category category) {
        return this.categoryService.save(category);
    }

    //通过id查询品牌下拥有的分类
    @GetMapping(value = "queryCategoryBrand/{bid}")
    public ResponseEntity<List<Category>> queryCategoryBrand(@PathVariable("bid") Long bid) {
        List<Category> list = categoryService.queryCategoryByBid(bid);
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
        return ResponseEntity.ok(categoryService.queryCategoryList(ids));
    }

}
