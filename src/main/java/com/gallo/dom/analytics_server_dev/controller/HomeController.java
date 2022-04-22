package com.gallo.dom.analytics_server_dev.controller;


import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTMLDocument;

@RestController
@RequestMapping(path = "/")
public class HomeController {


    @GetMapping(path = "/")
    public String getHome(){

        return "<h1> Hello World </h1>";
    }
}
