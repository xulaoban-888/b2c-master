package com.mr.bo;


import com.mr.pojo.SpecGroup;
import com.mr.pojo.SpecParam;
import lombok.Data;

import java.util.List;


@Data
public class SpecBo extends SpecGroup {

    private List<SpecParam> specParamList;
}
