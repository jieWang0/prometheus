package io.jprometheus.interceptor;

import io.prometheus.client.Summary;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrometheusRequestLatencyInterceptor extends HandlerInterceptorAdapter {

    static final Summary summary = Summary.build().labelNames("path", "method", "code")
            .name("Request_Latency_Total").help("Request latency in seconds.")
            .register();

    private Summary.Timer summaryTimer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        summaryTimer = summary.
                labels(request.getServletPath(), request.getMethod(), String.valueOf(response.getStatus())).startTimer();
        return super.preHandle(request,response,handler);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        summaryTimer.observeDuration();
        super.afterCompletion(request,response,handler,ex);
    }
}
