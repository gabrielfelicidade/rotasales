package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
        private val userService: UserService,
        private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)
) {

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        logger.info("Received request for create a user, request=$user")

        return try {
            val ret = userService.save(user)

            logger.info("New user saved, user=$ret")

            ResponseEntity.ok(ret)
        }catch(e: Exception) {
            logger.error("Error while inserting user, user=$user")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

}