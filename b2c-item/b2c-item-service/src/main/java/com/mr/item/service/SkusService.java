package com.mr.item.service;


import com.mr.item.mapper.SkusMapper;
import com.mr.pojo.Skus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkusService {


    @Autowired
    private SkusMapper skusMapper;


    public List<Skus> skusList() {


        List<Skus> skusList = skusMapper.selectAll();


        return skusList;
    }
}
