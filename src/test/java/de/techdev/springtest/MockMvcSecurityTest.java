package de.techdev.springtest;

import de.techdev.springtest.security.MethodSecurityConfiguration;
import de.techdev.springtest.security.SecurityConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Base class for mock mvc tests with the Spring-Security filter chain.
 * <p>
 * For this example this also loads the repositories and the Spring-Data-Rest configuration.
 *
 * @author Moritz Schulze
 */
@ContextConfiguration(classes = {AppConfiguration.class, WebConfiguration.class, SecurityConfiguration.class, MethodSecurityConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration
@WebAppConfiguration
public abstract class MockMvcSecurityTest {

    protected MockMvc mockMvc;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public final void initMockMvc() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).addFilter(filterChainProxy).build();
    }

    private MockHttpSession buildSession(Authentication authentication) {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, new MockSecurityContext(authentication));
        return session;
    }

    /**
     * @return a session for an employee with the id 500.
     */
    protected MockHttpSession employee() {
        return employee(500L);
    }

    protected MockHttpSession employee(Long id) {
        return buildSession(AuthenticationMocks.employeeAuthentication(id));
    }

    /**
     * @return a session for an admin with the id 0.
     */
    protected MockHttpSession admin() {
        return admin(0L);
    }

    protected MockHttpSession admin(Long id) {
        return buildSession(AuthenticationMocks.adminAuthentication(id));
    }

    private static class MockSecurityContext implements SecurityContext {
        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }
}
