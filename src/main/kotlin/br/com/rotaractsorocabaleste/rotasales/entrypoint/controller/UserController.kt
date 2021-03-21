package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)
) {

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        logger.info("Received request for create a user, username=${user.username}")

        val ret = userService.save(
            user.copy(
                password = passwordEncoder.encode(user.password)
            )
        )

        logger.info("New user saved, username=${ret.username}")

        return ResponseEntity.ok(ret)
    }

}