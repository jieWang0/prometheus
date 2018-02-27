package com.jw.prometheus.jprometheus.interceptor;

import io.prometheus.client.Counter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrometheusMetricsInterceptor extends HandlerInterceptorAdapter {

    static final Counter counter = Counter.build()
                                        .name("request_total")
                                        .labelNames("status","method")
                                        .help("Total Requests")
                                        .register();
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String method = request.getMethod();
        int status = response.getStatus();
        counter.labels(method,String.valueOf(status)).inc();
        super.afterCompletion(request, response, handler, ex);

    }
}
