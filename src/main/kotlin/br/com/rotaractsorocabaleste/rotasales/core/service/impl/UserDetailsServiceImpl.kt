package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.UserDetailsImpl
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        UserDetailsImpl(
            userService
                .findByUsername(username.toString())
                .first()
        )

}