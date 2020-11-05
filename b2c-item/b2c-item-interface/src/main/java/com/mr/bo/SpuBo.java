package com.mr.bo;


import com.mr.pojo.Sku;
import com.mr.pojo.Spu;
import com.mr.pojo.SpuDetail;
import lombok.Data;

import java.util.List;


@Data
public class SpuBo extends Spu {
    private String categoryName;// 商品分类名称

    private String brandName;// 品牌名称

    private SpuDetail spuDetail;// 商品详情

    private List<Sku> skus;// sku列表


}
