package de.techdev.springtest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import static java.util.Arrays.asList;

/**
 * @author Moritz Schulze
 */
public class TechdevUser extends User {

    private Long id;

    public TechdevUser(GrantedAuthority grantedAuthority, String username, String password, Long id) {
        super(username, password, asList(grantedAuthority));
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
