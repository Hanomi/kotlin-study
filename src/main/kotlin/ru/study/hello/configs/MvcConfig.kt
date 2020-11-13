package ru.study.hello.configs

import org.h2.server.web.WebServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.Servlet

@Configuration
class MvcConfig : WebMvcConfigurer {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun h2servletRegistration(): ServletRegistrationBean<*> {
        val registrationBean: ServletRegistrationBean<*> = ServletRegistrationBean<Servlet>(WebServlet())
        registrationBean.addUrlMappings("/console/*")
        return registrationBean
    }
}