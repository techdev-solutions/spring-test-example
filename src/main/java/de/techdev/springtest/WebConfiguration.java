package de.techdev.springtest;

import de.techdev.springtest.domain.EmployeeEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Spring-Data-Rest configuration.
 *
 * @author Moritz Schulze
 */
@Configuration
public class WebConfiguration extends RepositoryRestMvcConfiguration {

    @Bean
    public EmployeeEventHandler employeeEventHandler() {
        return new EmployeeEventHandler();
    }

}
