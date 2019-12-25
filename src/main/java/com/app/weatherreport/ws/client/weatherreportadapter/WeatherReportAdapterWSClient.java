package com.app.weatherreport.ws.client.weatherreportadapter;

import com.app.weatherreport.constant.Criteria;
import com.app.weatherreport.ws.client.WSClientUtil;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
public class WeatherReportAdapterWSClient {
    private RestTemplate restTemplate;

    private static final String CURRENT_WEATHER_REPORT_URI = "/api/weather/current/{criteria}";


    private static final Logger LOG = LoggerFactory.getLogger(WeatherReportAdapterWSClient.class);

    @Autowired
    public WeatherReportAdapterWSClient(@Qualifier("adapterWSTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCurrentWeatherReport(Criteria criteria, Map<String, String> params) {
        try {
            URI uri = getUri(CURRENT_WEATHER_REPORT_URI, params, criteria);
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET,
                    new HttpEntity<>(null), String.class);
            return WSClientUtil.handleResponse(responseEntity);
        } catch (Exception ex) {
            WSClientUtil.wrapException(ex);
            LOG.error(ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    private static URI getUri(String path, Map<String, String> queryParams,
                              Criteria criteria) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (MapUtils.isNotEmpty(queryParams)) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                params.add(entry.getKey(), entry.getValue());
            }
        }
        return UriComponentsBuilder.fromPath(path).queryParams(params).build(criteria);
    }
}
