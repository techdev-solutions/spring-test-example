package de.techdev.springtest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * @author Moritz Schulze
 */
public class TechdevUser implements UserDetails {

    private final GrantedAuthority grantedAuthority;
    private final String username;
    private final String password;
    private final Long id;

    public TechdevUser(GrantedAuthority grantedAuthority, String username, String password, Long id) {
        this.grantedAuthority = grantedAuthority;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return asList(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}
