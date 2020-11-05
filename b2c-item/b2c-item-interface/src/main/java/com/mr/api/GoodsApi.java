package com.mr.api;


import com.mr.bo.SpuBo;
import com.mr.common.utils.PageResult;
import com.mr.pojo.Sku;
import com.mr.pojo.Spu;
import com.mr.pojo.SpuDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "good")
public interface GoodsApi {

    @GetMapping(value = "page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable") Long saleable,
            @RequestParam(value = "search") String search
    );

    /**
     * 商品增加
     */
    @PostMapping
    void saveGoods(@RequestBody SpuBo spuBo);

    /**
     * 查询商品详情detail表信息回显 (修改回显detail表 ++ sku表:第一种方法)
     */
    @GetMapping(value = "/spu/detail/{spuId}")
    SpuDetail queryDetail(@PathVariable("spuId") Long spuId);

    /**
     * 查询sku表信息回显 sku集合
     */
    @GetMapping(value = "/skuList/{spuId}")
    List<Sku> querySku(@PathVariable("spuId") Long spuId);


    @PutMapping
    void updateGoods(@RequestBody SpuBo spuBo);

    /***
     * 删除数据
     */
    @DeleteMapping
    void deleteGoods(@RequestParam("id") Long id);

    /***
     *saleable数据商品上架或下架 ;第一种写法
     */
    @PutMapping("saleable")
    void saleableGoods(@RequestBody Spu spu);

    /*
     * 通过商品spuId查询数据(用于前台查询商品详情)
     * */
    @GetMapping(value = "/querySpuById/{id}")
    Spu querySpuById(@PathVariable("id") Long id);

    /**
     * 通过skuid查询sku的数据,用于购物车增加sku商品
     */
    @GetMapping(value = "querySkuById/{id}")
    Sku querySkuById(@PathVariable("id") Long skuId);
}