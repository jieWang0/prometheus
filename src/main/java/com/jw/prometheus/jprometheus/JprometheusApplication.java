package com.jw.prometheus.jprometheus;

import com.jw.prometheus.jprometheus.interceptor.PrometheusMetricsInterceptor;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class JprometheusApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(JprometheusApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PrometheusMetricsInterceptor()).addPathPatterns("/**");
	}

}
