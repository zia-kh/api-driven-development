package com.udemy.openapidemo.config



import org.openapitools.SpringDocConfiguration
import org.springdoc.core.properties.SpringDocConfigProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun springDocConfiguration(): SpringDocConfiguration = SpringDocConfiguration()

    @Bean
    fun springDocConfigProperties(): SpringDocConfigProperties = SpringDocConfigProperties()
}