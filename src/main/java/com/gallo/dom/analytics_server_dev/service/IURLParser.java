package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.model.requests.PageViewRequest;

public interface IURLParser {
    PageViewRequest parseURL(String urlRequestParameter);

}
