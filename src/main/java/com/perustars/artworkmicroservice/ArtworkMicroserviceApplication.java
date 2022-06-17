package com.perustars.artworkmicroservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;*/

@OpenAPIDefinition
@SpringBootApplication
public class ArtworkMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtworkMicroserviceApplication.class, args);
    }
    /*@Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() { return WebClient.builder(); }
    */
}

