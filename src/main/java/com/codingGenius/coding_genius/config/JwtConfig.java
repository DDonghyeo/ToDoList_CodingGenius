package com.codingGenius.coding_genius.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"/application.yml"}, factory = YamlPropertySourceFactory.class)
public class JwtConfig {

    @Value("${jwt.secret")
    public static String SECRET_KEY;
}
