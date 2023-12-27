package com.sharkend.jwttemplate.Controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
// Swagger:
@Tag(name = "Changed name of this controller")
//@Hidden //works on individual methods (endpoints) and entire classes (controllers)
public class ManagementController {
    @GetMapping
    @Operation(
            description = "custom swagger endpoint description",
            summary = "custom summary",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "custom description for response code"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized: Invalid Token"
                    )
            }
    )
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
