package com.ruoyi.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guo Email:guoshuaihui20@mails.ucas.ac.cn
 * @date 2021/12/21 19:44
 */
@RestController
@RequestMapping("/repair-system")
public class testGuoController {
    @GetMapping("/test")
    public String guoTest(){
        return "guo";
    }
}
