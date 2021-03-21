package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import br.com.rotaractsorocabaleste.rotasales.core.exception.UserUnauthorizedException
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    @Transactional
    override fun save(user: User): User = userRepository.save(user)

    override fun findByUsername(username: String): List<User> = userRepository.findByUsername(username)

    override fun getLoggedInUser(): User =
        findByUsername(
            SecurityContextHolder.getContext().authentication.name
        ).firstOrNull() ?: throw UserUnauthorizedException(null)
}