package br.com.rotaractsorocabaleste.rotasales.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid final User user) {
        return userService.create(user.withPassword(passwordEncoder.encode(user.getPassword())));
    }

    @PatchMapping
    public void changePassword(@RequestBody @Valid final ChangePasswordDTO changePasswordDTO) {
        userService.changePassword(changePasswordDTO.withPassword(passwordEncoder.encode(changePasswordDTO.getPassword())));
    }

}
