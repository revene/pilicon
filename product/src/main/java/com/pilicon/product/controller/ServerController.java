package com.pilicon.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @RequestMapping(value = "msg",method = RequestMethod.GET)
    public String msg(){
        return "this is product'msg ";
    }
}
