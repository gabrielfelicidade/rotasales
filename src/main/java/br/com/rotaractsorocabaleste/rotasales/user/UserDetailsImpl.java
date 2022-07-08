package br.com.rotaractsorocabaleste.rotasales.user;

import br.com.rotaractsorocabaleste.rotasales.userrole.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class UserDetailsImpl implements UserDetails, Serializable {

    private static final long serialVersionUID = 2564019897965259451L;
    private final Set<GrantedAuthority> authorities = new HashSet<>();
    private final User user;

    public UserDetailsImpl(final User user) {
        this.user = user;
        initAuthorities(user);
    }

    private void initAuthorities(User user) {
        if (isNull(user.getRoles())) {
            return;
        }
        for (UserRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getDescription()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

}
