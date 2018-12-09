package com.web.sell.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class SellerReq {

    @ApiModelProperty(value = "商户id", required = true)
    @NotNull
    private Long id;

}
