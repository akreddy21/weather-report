package com.app.weatherreport.service;

import com.app.weatherreport.constant.Criteria;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WeatherReportService {
    String getCurrentWeatherReport(Criteria criteria, Map<String, String> params);
}
