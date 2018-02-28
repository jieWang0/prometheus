package io.jprometheus.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable the prometheus and registry it's servlet into your application
 *
 * usage: just add this annotation to your spring boot application
 * {@literal @}SpringBootApplication
 * {@literal @}EnableTccPrometheus
 *  public class Application {
 *    public static void main(String[] args) {
 *      SpringApplication.run(Application.class, args);
 *    }
 *  }
 *@author wangjie
 * */

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Import(PrometheusConfigBean.class)
@Documented
public @interface EnableTccPrometheus {
}
