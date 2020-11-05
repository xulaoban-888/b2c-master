package com.mr.item.service;

import com.mr.item.mapper.SpecGroupMapper;
import com.mr.pojo.SpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpecGroupService {
    @Autowired
    private SpecGroupMapper specGroupMapper;

    //查询
    public List<SpecGroup> querySpecGroup(Long cid) {

        //创建过滤条件类
        Example example = new Example(SpecGroup.class);

        //通过过滤条件类,构建实例,实体字段和参数相等
        example.createCriteria().andEqualTo("cid", cid);
        //设置条件查询
        return specGroupMapper.selectByExample(example);
    }

    //增加
    public ResponseEntity saveOrUpdateSpecGroup(SpecGroup specGroup) {
        if (specGroup != null) {
            if (specGroup.getId() != null && specGroup.getId() != 0) {
                return ResponseEntity.ok(specGroupMapper.updateByPrimaryKey(specGroup));
            } else {

                return ResponseEntity.ok(specGroupMapper.insertSelective(specGroup));
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //删除
    public ResponseEntity findById(Long id) {
        try {
            if (id != null && id != 0) {

                specGroupMapper.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("删除成功");
    }



/*
    public void addCollect(Long skuId) {
        if (skuId != 0 && skuId != null) {
            Sku sku = skuMapper.selectByPrimaryKey(skuId);
            Skus skus = new Skus();
            skus.setId(sku.getId());
            skus.setSpuId(sku.getSpuId());
            skus.setTitle(sku.getTitle());
            skus.setImages(sku.getImages());
            skus.setPrice(sku.getPrice());
            skus.setOwnSpec(sku.getOwnSpec());
            skus.setIndexes(sku.getIndexes());
            skus.setEnable(sku.getEnable());
            skus.setCreateTime(sku.getCreateTime());
            skus.setLastUpdateTime(sku.getLastUpdateTime());

            System.out.println(sku);
            skusMapper.insertSelective(skus);
        }
    }*/
}
