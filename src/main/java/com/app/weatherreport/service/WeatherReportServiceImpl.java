package com.app.weatherreport.service;

import com.app.weatherreport.constant.Criteria;
import com.app.weatherreport.ws.client.weatherreportadapter.WeatherReportAdapterWSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeatherReportServiceImpl implements WeatherReportService {

    private WeatherReportAdapterWSClient weatherReportAdapterWSClient;
    private static final Logger LOG = LoggerFactory.getLogger(WeatherReportServiceImpl.class);

    @Autowired
    public WeatherReportServiceImpl(WeatherReportAdapterWSClient weatherReportAdapterWSClient) {
        this.weatherReportAdapterWSClient = weatherReportAdapterWSClient;
    }

    @Override
    public String getCurrentWeatherReport(Criteria criteria, Map<String, String> params) {
        return weatherReportAdapterWSClient.getCurrentWeatherReport(criteria, params);
    }
}
