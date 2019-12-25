package com.app.weatherreport.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "adapterWSTemplate")
    public RestTemplate adapterWSTemplate(RestTemplateBuilder builder, AppConfigProperties properties) {
        AppConfigProperties.WeatherReportAdapterWSProperties adapterWSProperties = properties.getAdapterWSProperties();
        return builder.rootUri(adapterWSProperties.getRootUri())
                .setReadTimeout(adapterWSProperties.getReadTimeout())
                .setConnectTimeout(adapterWSProperties.getConnectTimeout())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .requestFactory((Supplier<ClientHttpRequestFactory>) new HttpComponentsClientHttpRequestFactory())
                .errorHandler(new DefaultResponseErrorHandler()).build();
    }
}


