package com.mr.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mr.bo.SpuBo;
import com.mr.common.utils.PageResult;
import com.mr.item.mapper.BrandMapper;
import com.mr.item.mapper.CategoryMapper;
import com.mr.item.mapper.SpuMapper;
import com.mr.pojo.Category;
import com.mr.pojo.Spu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsService {

    @Resource
    private SpuMapper spuMapper;
    @Resource
    private BrandMapper brandMapper;
    @Resource
    private CategoryMapper categoryMapper;

    public PageResult<SpuBo> querySpuPage(Integer page, Integer rows, Long saleable, String search) {
        //分页 : 使用的是分页助手启动器 pagehelper ;pom.xml引入的依赖
        //设置当前页和每页条数; limit第一个参数表示从该参数的下一条数据开始，第二个参数表示每次返回的数据条数。
        PageHelper.startPage(page, rows);

        //过滤条件 前台搜索searchKey使用的模糊查询
        //创建过滤条件类
        Example example = new Example(Spu.class);
        //通过过滤条件类,构建实例,
        Example.Criteria criteria = example.createCriteria();

        // 是否过滤上下架(实体字段,参数):controller默认为true,不用加判断
        //andEqualTo:数据库(实体)与输入值(参数)做对比
        if (saleable != 2 && saleable != null) {

            //上架:1;下架:0;展示全部:2
            criteria.andEqualTo("saleable", saleable);
        }

        //设置模糊查询;根据title名称,进行模糊查询
        if (StringUtil.isNotEmpty(search)) {//判断search搜索不为空
            criteria.andLike("title", "%" + search + "%");
        }

        //使用分页助手提供的类接收分页参数和实体参数; Page拦截这个方法,会追加一个count统计数据的sql
        //mapper查询只能返回是Spu,因为查询数据,实体Spu对应数据库
        Page<Spu> pageData = (Page<Spu>) spuMapper.selectByExample(example);
        //返回值加工Spu-->SpuBo 返回的是分页的信息
        List<Spu> spuList = pageData.getResult();
        System.out.println(spuList);

        //拉姆达表达式循环 数据,填充品牌名 分类三级名
        //原来pageData.getResult()的返回值是List<Spu>(分页的信息)类型的数组;
        // 通过stream将数组转成流 循环操作;返回填充信息
        List<SpuBo> spuBoList = spuList.stream().map(spu -> { //spu循环体
            SpuBo spuBo = new SpuBo();
            //通过工具类,复制对象中的属性,第一个是要复制的属性,第二个是目标属性
            BeanUtils.copyProperties(spu, spuBo);

            //填充品牌brand
            spuBo.setBrandName(this.brandMapper.selectByPrimaryKey(spuBo.getBrandId()).getName());

            //填充分类三级名  "/手机/手机通讯/手机"
            //使用mapper批量查询分类category实体的3个id
            List<Category> categorylist = this.categoryMapper.selectByIdList(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())
            );
            //循环获取的3个id提取Name列; 通过stream将数组转成流 循环操作;
            List<String> categoryName = categorylist.stream().map(category ->
                    category.getName()
            ).collect(Collectors.toList());

            //设置到bo中;将List join  / 链接
            spuBo.setCategoryName(StringUtils.join(categoryName, "/"));
            return spuBo;
        }).collect(Collectors.toList());//最后通过流转成后返回想要的SpuBo泛型数组(List<SpuBo>)

        //spuBoList 最后输出的是填充信息
        System.out.println(spuBoList);
        return new PageResult<SpuBo>(pageData.getTotal(), spuBoList);
    }


}
