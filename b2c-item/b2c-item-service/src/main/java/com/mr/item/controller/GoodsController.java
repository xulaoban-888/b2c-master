package com.mr.item.controller;

import com.mr.bo.SpuBo;
import com.mr.common.utils.PageResult;
import com.mr.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "good")
public class GoodsController {
    @Autowired
    private GoodsService service;

    @GetMapping(value = "page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable") Long saleable,
            @RequestParam(value = "search") String search
    ) {
        PageResult<SpuBo> pageResult = service.querySpuPage(page, rows, saleable, search);
        if (pageResult == null && pageResult.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pageResult);
    }

}
