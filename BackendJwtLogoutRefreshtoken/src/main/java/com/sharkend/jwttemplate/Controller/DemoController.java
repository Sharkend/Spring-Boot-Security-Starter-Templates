package com.sharkend.jwttemplate.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample controller to test jwt authentication (non-critical)
 */
@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Secure hello world");
    }
}
