package com.web.sell.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.sell.model.Seller;
import com.web.sell.req.SellerReq;
import com.web.sell.service.SellerService;
import com.web.sell.util.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "卖家相关接口")
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @ApiOperation(value = "卖家信息")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Res<?> detail(@RequestParam("id") Long gid) {
        Seller seller = sellerService.findOne(gid);
        return Res.buildOk(seller);
    }

    @ApiOperation(value = "卖家列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Res<?> list(@RequestBody SellerReq sellerReq) {
        PageHelper.startPage(sellerReq.getPageNo(), sellerReq.getPageSize());
        List<Seller> list = sellerService.listByPage(sellerReq);
        PageInfo<Seller> pageInfo = new PageInfo<>(list);
        return Res.buildOk(pageInfo);
    }


}
