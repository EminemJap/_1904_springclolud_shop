package com.qf.controller;

import com.alibaba.fastjson.JSONArray;
import com.qf.service.FindShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindShopController {
    @Autowired
    private FindShopService findShopService;
    public JSONArray findShop(@RequestParam("name")String name){
       JSONArray s=findShopService.find(name);
        return s;
    }
}
