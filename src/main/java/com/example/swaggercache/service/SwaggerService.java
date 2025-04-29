package com.example.swaggercache.service;

import com.example.swaggercache.entity.SwaggerEntity;
import com.example.swaggercache.repository.SwaggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwaggerService {

    private final SwaggerRepository repository;

    @CachePut(value = "swagger", key = "#entity.clientId")
    public SwaggerEntity saveSwagger(SwaggerEntity entity) {
        return repository.save(entity);
    }

    @Cacheable(value = "swagger", key = "#clientId")
    public SwaggerEntity getSwagger(String clientId) {
        return repository.findById(clientId).orElse(null);
    }
}
