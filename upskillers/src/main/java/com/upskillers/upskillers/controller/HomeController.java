package com.upskillers.upskillers.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String index(@RequestParam(value = "name", defaultValue = "you") String name) {
        return "Hi " + name + " ^^";
    }
    
}
