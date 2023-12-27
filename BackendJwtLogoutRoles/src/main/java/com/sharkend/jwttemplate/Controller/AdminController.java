package com.sharkend.jwttemplate.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')") //Instead of using requestMatchers in SecurityConfig
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')") //from Permission.java
    public String get() {
        return "ADMIN::GET";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String post() {
        return "ADMIN::POST";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String put() {
        return "ADMIN::PUT";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete() {
        return "ADMIN::DELETE";
    }
}
