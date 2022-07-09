package br.com.rotaractsorocabaleste.rotasales.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User create(final User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findLoggedInUser() {
        return findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).orElseThrow();
    }

    @Override
    public void changePassword(final ChangePasswordDTO changePasswordDTO) {
        final var user = findLoggedInUser();

        userRepository.save(user.withPassword(changePasswordDTO.getPassword()));
    }

    @Override
    @Transactional
    public Collection<GrantedAuthority> findAuthoritiesByUsername(final String username) {
        final var user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                format("User: %s, not found", username)
        ));

        return new UserDetailsImpl(user).getRoles();
    }

}
