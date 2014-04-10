package de.techdev.springtest;

import de.techdev.springtest.domain.EmployeeDataOnDemand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Moritz Schulze
 */
@Configuration
public class DataOnDemandConfiguration {

    @Bean
    public EmployeeDataOnDemand employeeDataOnDemand() {
        return new EmployeeDataOnDemand();
    }
}
