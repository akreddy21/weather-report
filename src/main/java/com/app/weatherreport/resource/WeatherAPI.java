package com.app.weatherreport.resource;


import com.app.weatherreport.constant.Criteria;
import com.app.weatherreport.service.WeatherReportService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/weather/current", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherAPI {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherAPI.class);
    private WeatherReportService weatherReportService;

    @Autowired
    public WeatherAPI(WeatherReportService weatherReportService) {
        this.weatherReportService = weatherReportService;
    }

    @GetMapping(params = {"postalCode", "country"})
    public String getCurrentWeatherReportByPostalCode(@RequestParam String postalCode,
                                                      @RequestParam String country) {
        validateData(postalCode, country);
        Map<String, String> params = new HashMap<>();
        params.put("postalCode", postalCode);
        params.put("country", country);
        return weatherReportService.getCurrentWeatherReport(Criteria.postal_code, params);
    }

    @GetMapping(params = {"lat", "lon"})
    public String getCurrentWeatherReportByLatLon(@RequestParam String lat,
                                                  @RequestParam String lon) {
        validateData(lat, lon);
        Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);
        return weatherReportService.getCurrentWeatherReport(Criteria.lat_lon, params);
    }

    private void validateData(String... params) {
        if (StringUtils.isAllBlank(params)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
