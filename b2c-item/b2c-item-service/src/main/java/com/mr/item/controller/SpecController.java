package com.mr.item.controller;


import com.mr.item.service.SpecGroupService;
import com.mr.item.service.SpecParamService;
import com.mr.pojo.SpecGroup;
import com.mr.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "spec")
public class SpecController {
    @Autowired
    private SpecGroupService specGroupService;
    @Autowired
    private SpecParamService specParamService;

    //规格查询
    @GetMapping(value = "groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroup(@PathVariable("cid") Long cid) {
        //返回分类下的规格
        List<SpecGroup> list = specGroupService.querySpecGroup(cid);
        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);

    }

    //规格增加
    @PostMapping(value = "group")
    public ResponseEntity saveSpecGroup(@RequestBody SpecGroup specGroup) {

        return specGroupService.saveOrUpdateSpecGroup(specGroup);
    }

    //规格修改(根据实体修改)
    @PutMapping(value = "group")
    public ResponseEntity updateSpecGroup(@RequestBody SpecGroup specGroup) {

        return specGroupService.saveOrUpdateSpecGroup(specGroup);
    }

    //规格删除
    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") Long id) {
        return this.specGroupService.findById(id);
    }


    //规格参数查询
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> querySpecParam(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic
    ) {
        List<SpecParam> list = this.specParamService.querySpecParams(gid, cid, searching, generic);
        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    //规格参数增加
    @PostMapping(value = "param")
    public ResponseEntity addSpecParam(@RequestBody SpecParam specParam) {

        return specParamService.saveOrUpdateSpecParam(specParam);
    }

    //规格参数修改
    @PutMapping(value = "param")
    public ResponseEntity editSpecParam(@RequestBody SpecParam specParam) {

        return specParamService.saveOrUpdateSpecParam(specParam);
    }

    //规格参数删除
    @DeleteMapping(value = "param/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        return this.specParamService.findById(id);
    }


}
