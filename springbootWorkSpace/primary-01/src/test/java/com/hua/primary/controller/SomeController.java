package com.hua.primary.controller;

import com.hua.primary.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author
 * @since
 */
@RestController
public class SomeController {

    @Autowired
    private MsgService msgService;

    @GetMapping("/test")
    public String test(){
        return "hello word 111";
    }

    @GetMapping("/test/500")
    public String test500(){
        int i = 50/0;
        return "hello word 111";
    }

    @GetMapping("/profile")
    public String profile(){
        return msgService.message();
    }
}
