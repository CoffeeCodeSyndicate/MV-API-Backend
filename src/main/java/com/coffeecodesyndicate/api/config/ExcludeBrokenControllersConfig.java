// src/main/java/com/coffeecodesyndicate/api/config/ExcludeBrokenControllersConfig.java
package com.coffeecodesyndicate.api.config;

import com.coffeecodesyndicate.api.controllers.AdminController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.coffeecodesyndicate.api",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = AdminController.class
    )
)
public class ExcludeBrokenControllersConfig {}
