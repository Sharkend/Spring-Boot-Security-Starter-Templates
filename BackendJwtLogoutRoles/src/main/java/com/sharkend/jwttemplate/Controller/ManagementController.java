package com.sharkend.jwttemplate.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementController {
    @GetMapping
    public String get() {
        return "MANAGEMENT::GET";
    }

    @PostMapping
    public String post() {
        return "MANAGEMENT::POST";
    }

    @PutMapping
    public String put() {
        return "MANAGEMENT::PUT";
    }

    @DeleteMapping
    public String delete() {
        return "MANAGEMENT::DELETE";
    }
}
