package com.glop.cibl_orga_sport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingRestController {

    @GetMapping("/api/ping")
    public String ping() {
        return "pong from Spring Boot!";
    }
}
