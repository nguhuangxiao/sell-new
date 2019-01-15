package com.web.sell.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class SellerReq extends BaseReg{

    @ApiModelProperty(value = "商户id")
    private Long id;

    @ApiModelProperty(value = "关键字")
    private String keyWord;

}
