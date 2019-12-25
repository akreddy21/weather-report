package com.app.weatherreport.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties({AppConfigProperties.class})
public class AppConfig {

    @Autowired
    private AppConfigProperties appConfigProperties;
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com"))
//                .paths(PathSelectors.any())
//                .build();
//    }
}
