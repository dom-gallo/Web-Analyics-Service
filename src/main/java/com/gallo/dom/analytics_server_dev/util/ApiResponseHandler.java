package com.gallo.dom.analytics_server_dev.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/*
    To have a standardized response entity across all API endpoints.

 */

public class ApiResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status_code, Object responseObject)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status_code.value());
        map.put("data", responseObject);

        return new ResponseEntity<>(map, status_code);
    }
}
