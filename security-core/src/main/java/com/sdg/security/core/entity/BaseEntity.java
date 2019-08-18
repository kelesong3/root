package com.sdg.security.core.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class BaseEntity {
    @ApiModelProperty(value = "ID",dataType = "BigInteger类型",required = true)
    private BigInteger id;
    @ApiModelProperty(value = "操作者",dataType = "String类型",required = true)
    private String operator;
    @ApiModelProperty(value = "创建时间",dataType = "时间戳类型",required = false)
    private Date createdTime;
    @ApiModelProperty(value = "更新时间",dataType = "时间戳类型",required = false)
    private Date updatedTime;





}
