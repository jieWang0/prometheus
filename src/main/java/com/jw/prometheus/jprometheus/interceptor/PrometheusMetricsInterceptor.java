package com.jw.prometheus.jprometheus.interceptor;

import io.prometheus.client.Counter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrometheusMetricsInterceptor extends HandlerInterceptorAdapter {

    static final Counter counter = Counter.build()
                                        .name("myRequest_total")
                                        .labelNames("method","path","status")
                                        .help("Total Requests")
                                        .register();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String method = request.getMethod();
        int status = response.getStatus();
        String path = request.getServletPath();
        counter.labels(method,path,String.valueOf(status)).inc();

    }
}
