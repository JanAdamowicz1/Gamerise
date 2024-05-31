package com.example.gamerise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");

        registry.addMapping("/images/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile-pictures/**")
                .addResourceLocations("file:src/main/resources/static/profile-pictures/")
                .setCachePeriod(0);
    }
}