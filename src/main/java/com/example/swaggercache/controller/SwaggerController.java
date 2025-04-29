package com.example.swaggercache.controller;

import com.example.swaggercache.entity.SwaggerEntity;
import com.example.swaggercache.service.SwaggerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/swagger")
@RequiredArgsConstructor
@Tag(name = "Swagger Management", description = "APIs to upload and fetch Swagger JSON documents")
public class SwaggerController {

    private final SwaggerService service;

    @PostMapping
    @Operation(
        summary = "Save Swagger JSON",
        description = "Accepts clientId and Swagger JSON, saves it to H2 and caches in Redis"
    )
    public ResponseEntity<SwaggerEntity> saveSwagger(@RequestBody SwaggerEntity entity) {
        return ResponseEntity.ok(service.saveSwagger(entity));
    }

    @GetMapping("/{clientId}")
    @Operation(
        summary = "Get Swagger JSON by Client ID",
        description = "Fetches Swagger JSON using clientId from cache (or DB if not in cache)"
    )
    public ResponseEntity<SwaggerEntity> getSwagger(@PathVariable String clientId) {
        SwaggerEntity swaggerEntity = service.getSwagger(clientId);
        if (swaggerEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(swaggerEntity);
    }
}
