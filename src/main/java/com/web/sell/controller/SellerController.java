package com.web.sell.controller;

import com.web.sell.model.Seller;
import com.web.sell.service.SellerService;
import com.web.sell.util.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "卖家相关接口")
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @ApiOperation(value = "卖家信息")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Res<?> list(@RequestParam("id") Long gid) {

        Seller seller = sellerService.findOne(gid);

        return Res.buildOk(seller);
    }

}
