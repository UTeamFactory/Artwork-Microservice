package com.perustars.artworkmicroservice;

import com.perustars.artworkmicroservice.config.AxonConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition
@SpringBootApplication
@EnableEurekaClient
@Import({AxonConfig.class})
public class ArtworkMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtworkMicroserviceApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() { return WebClient.builder(); }
}

