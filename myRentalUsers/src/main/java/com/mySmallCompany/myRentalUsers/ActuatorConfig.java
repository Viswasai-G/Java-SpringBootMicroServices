//package com.mySmallCompany.myRentalUsers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.autoconfigurer.EndpointAutoConfiguration;
//import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
//import org.springframework.boot.actuate.autoconfigure.PublicMetricsAutoConfiguration;
//import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
//import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
//import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
//import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.util.Collection;
//
//@Configuration
//@EnableWebMvc
//@Import({
//        EndpointAutoConfiguration.class , PublicMetricsAutoConfiguration.class , HealthIndicatorAutoConfiguration.class
//})
//public class ActuatorConfig {
//
//
//    @Bean
//    @Autowired
//    public EndpointHandlerMapping endpointHandlerMapping(Collection<? extends MvcEndpoint> endpoints) {
//        return new EndpointHandlerMapping(endpoints);
//    }
//
//    @Bean
//    @Autowired
//    public EndpointMvcAdapter metricsEndPoint(MetricsEndpoint delegate) {
//        return new EndpointMvcAdapter(delegate);
//    }
//}