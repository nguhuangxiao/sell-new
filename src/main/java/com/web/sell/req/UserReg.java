package com.web.sell.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserReg {

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

}
