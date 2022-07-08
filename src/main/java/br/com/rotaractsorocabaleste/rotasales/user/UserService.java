package br.com.rotaractsorocabaleste.rotasales.user;

import java.util.Optional;

public interface UserService {

    User create(final User user);

    Optional<User> findByUsername(final String username);

    User findLoggedInUser();

    void changePassword(final ChangePasswordDTO changePasswordDTO);
}
