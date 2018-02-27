package com.jw.prometheus.jprometheus.configuration;

import io.prometheus.client.Counter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrometheusConfigBean {
    @Bean
    public Counter CounterFactory() {
        return Counter.build()
                .name("request_total")
                .labelNames("status","method")
                .help("Total Requests")
                .register();
    }
}
