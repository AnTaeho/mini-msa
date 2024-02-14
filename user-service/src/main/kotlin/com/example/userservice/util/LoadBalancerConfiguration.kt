package com.example.userservice.util

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean

class LoadBalancerConfiguration {

    @Bean
    fun instanceSupplier(context: ConfigurableApplicationContext): ServiceInstanceListSupplier {
        return ServiceInstanceListSupplier.builder()
            .withDiscoveryClient()
            .withHealthChecks()
            .build(context)
    }

}