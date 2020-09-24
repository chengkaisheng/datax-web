package com.wugui.datax.search.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iwlnner on 2020/9/11.
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @RequestMapping("/getCreateMsg")
    public String getCreateMsg(String username){

        return "";
    }

}
