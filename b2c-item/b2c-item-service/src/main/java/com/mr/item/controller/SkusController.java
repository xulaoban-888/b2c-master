package com.mr.item.controller;


import com.mr.item.service.SkusService;
import com.mr.pojo.Skus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("skus")
public class SkusController {


    @Autowired
    private SkusService skusService;


    @GetMapping("list")
    public ResponseEntity<List<Skus>> skusList() {

        List<Skus> skusList = skusService.skusList();

        return ResponseEntity.ok(skusList);
    }


}
