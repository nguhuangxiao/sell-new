package com.web.sell.req;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserReg {

    @NotNull
    private long gid;

    @NotEmpty(message = "用户名不能为空")
    private String name;

    private String password;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    private Integer sex;

    private Date createTime;

    private Date updateTime;

}
