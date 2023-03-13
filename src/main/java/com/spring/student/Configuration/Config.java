package com.spring.student.Configuration;

import com.spring.student.model.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class Config implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] preventMethods = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
        config.exposeIdsFor(Student.class)
                .getExposureConfiguration()
                .forDomainType(Student.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(preventMethods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(preventMethods));

    }
}
