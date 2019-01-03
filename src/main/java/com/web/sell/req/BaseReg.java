package com.web.sell.req;

import lombok.Data;

/**
 * @Description: 公共
 * @Author: nguhuangxiao
 * @Date: 2019/1/3
 */
@Data
public class BaseReg {

    private final int PAGE_START = 1;

    private final int DEF_PAGE_SIZE = 10;

    private int pageNo = PAGE_START;

    private int pageSize = DEF_PAGE_SIZE;


}
