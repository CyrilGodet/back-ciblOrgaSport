package com.glop.cibl_orga_sport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "RolesController OK !";  // 🔥 SIMPLE
    }
}