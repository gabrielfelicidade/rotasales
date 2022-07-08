package br.com.rotaractsorocabaleste.rotasales.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
