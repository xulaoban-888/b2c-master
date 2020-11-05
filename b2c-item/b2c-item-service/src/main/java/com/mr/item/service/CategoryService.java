package com.mr.item.service;

import com.mr.common.enums.ExceptionEnums;
import com.mr.common.exception.MrException;
import com.mr.item.mapper.CategoryMapper;
import com.mr.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryListByParent(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    public void deleteCategory(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    //增加/修改
    public ResponseEntity save(Category category) {
        try {
            if (category != null) {
                if (category.getId() != null && category.getId() != 0) {
                    this.categoryMapper.updateByPrimaryKeySelective(category);
                } else {
                    this.categoryMapper.insertSelective(category);
                }
            }
        } catch (MrException e) {
            e.printStackTrace();
            throw new MrException(ExceptionEnums.SAVE_UPDATE_CATEGORY_ERROR);
        }
        return ResponseEntity.ok("操作成功");
    }


    //根据id修改
    public ResponseEntity<Category> findById(Long id) {
        if (id == null) {
            throw new MrException(ExceptionEnums.SELECT_KEY_CATEGORY_ERROR);
        }
        Category category = this.categoryMapper.selectByPrimaryKey(id);
        return ResponseEntity.ok(category);
    }

    /**
     * 通过品牌id查询分类数据(用于品牌新增,把品牌新增到分类下面)
     */
    public List<Category> queryCategoryByBid(Long bid) {
        return this.categoryMapper.queryCategoryBrand(bid);
    }


    //用于es库,根据分类ids,查询多个分类
    public List<Category> queryCategoryList(List<Long> ids) {
        return categoryMapper.selectByIdList(ids);
    }

}