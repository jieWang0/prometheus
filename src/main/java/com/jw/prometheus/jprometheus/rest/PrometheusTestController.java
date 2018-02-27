package com.jw.prometheus.jprometheus.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jprometheus")
public class PrometheusTestController {

    @RequestMapping("first")
    public void firstMethod() {
        System.out.println("receive first one");
    }

    @RequestMapping("second")
    public void secondMethod() {
        System.out.println("receive second one");
    }
}
