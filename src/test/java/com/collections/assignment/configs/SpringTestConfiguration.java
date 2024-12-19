package com.collections.assignment.configs;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.net.http.HttpClient;

@Configuration
@ComponentScan(basePackages = {"com.collections.assignment.actions", "com.collections.assignment.configs"})
public class SpringTestConfiguration {
    @Bean
    public HttpClient noSSLHTTPClient() {
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    }
}
