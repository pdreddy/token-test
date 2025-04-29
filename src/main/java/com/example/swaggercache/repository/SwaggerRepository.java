package com.example.swaggercache.repository;

import com.example.swaggercache.entity.SwaggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwaggerRepository extends JpaRepository<SwaggerEntity, String> {
}
