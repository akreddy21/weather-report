package com.app.weatherreport.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

public final class WSClientUtil {
    private static final Logger LOG = LoggerFactory.getLogger(WSClientUtil.class);

    private WSClientUtil() {

    }

    public static <T> T handleResponse(ResponseEntity<T> responseEntity) {
        if (responseEntity != null && responseEntity.getStatusCode() != null
                && HttpStatus.OK.compareTo(responseEntity.getStatusCode()) == 0) {
            return responseEntity.getBody();
        }
        return null;
    }

    public static void wrapException(Exception ex) {
        if (ex instanceof HttpStatusCodeException) {
            HttpStatusCodeException codeException = (HttpStatusCodeException) ex;
            LOG.error("service response error: status_code: {}, response_body: {}", codeException.getStatusCode(),
                    codeException.getResponseBodyAsString());
//            LOG.error(codeException.getMessage(), codeException);
        } else if (ex instanceof RestClientException) {
            RestClientException rcx = (RestClientException) ex;
            LOG.error("exception occurred while connecting to service,", rcx);
        }
    }

}
