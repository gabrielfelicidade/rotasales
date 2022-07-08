package br.com.rotaractsorocabaleste.rotasales.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceImpl userServiceImpl;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userServiceImpl.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                format("User: %s, not found", username)
        )));
    }

}
