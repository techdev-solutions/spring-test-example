package de.techdev.springtest;

import de.techdev.springtest.security.TechdevUser;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * @author Moritz Schulze
 */
public class AuthenticationMocks {
    private AuthenticationMocks() {
    }

    public static Authentication employeeAuthentication(Long id) {
        TechdevUser techdevUser = new TechdevUser(() -> "ROLE_EMPLOYEE", "employee", "employee", id);
        return new TestingAuthenticationToken(techdevUser, null, "ROLE_EMPLOYEE");
    }

    public static Authentication adminAuthentication(Long id) {
        TechdevUser techdevUser = new TechdevUser(() -> "ROLE_ADMIN", "admin", "admin", id);
        return new TestingAuthenticationToken(techdevUser, null, "ROLE_ADMIN");
    }
}
