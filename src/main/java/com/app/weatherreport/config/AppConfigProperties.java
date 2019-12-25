package com.app.weatherreport.config;

import com.app.weatherreport.constant.Criteria;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "properties")
public class AppConfigProperties {

    private WeatherReportAdapterWSProperties adapterWSProperties;

    @Data
    public static class WeatherReportAdapterWSProperties {
        private String rootUri;
        private Duration connectTimeout;
        private Duration readTimeout;
    }
}