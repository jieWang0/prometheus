package io.jprometheus.interceptor;

import io.prometheus.client.Gauge;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrometheusActiveRequestInterceptor extends HandlerInterceptorAdapter {

    static final Gauge activeRequests = Gauge.build()
            .name("Active_Request_Total").labelNames("path", "method", "code")
            .help("Active Requests").register();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        activeRequests.labels(request.getServletPath(), request.getMethod(), String.valueOf(response.getStatus())).inc();
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        activeRequests.labels(request.getServletPath(), request.getMethod(), String.valueOf(response.getStatus())).dec();
        super.afterCompletion(request, response, handler, ex);
    }
}
