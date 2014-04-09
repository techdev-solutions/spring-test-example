package de.techdev.springtest;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author Moritz Schulze
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfiguration.class, MethodSecurityConfiguration.class, AppConfiguration.class);
    }

}
