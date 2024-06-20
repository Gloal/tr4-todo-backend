package com.gloal.todo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("datasource")
public record TodoConfigProperties(String username, String password, String url) {
}
