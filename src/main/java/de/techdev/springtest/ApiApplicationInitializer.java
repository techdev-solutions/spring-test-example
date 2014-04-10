package de.techdev.springtest;

import de.techdev.springtest.security.MethodSecurityConfiguration;
import de.techdev.springtest.security.SecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Servlet 3.0 web application initializer. See also {@link de.techdev.springtest.security.SecurityWebApplicationInitializer}.
 *
 * @author Moritz Schulze
 */
public class ApiApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {SecurityConfiguration.class, MethodSecurityConfiguration.class, AppConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
