package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import br.com.rotaractsorocabaleste.rotasales.core.entity.UserRole
import br.com.rotaractsorocabaleste.rotasales.core.service.UserRoleService
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userRoleService: UserRoleService
) : UserService {
    @Transactional
    override fun save(user: User): User {
        val savedUser = userRepository.save(user.copy(roles = listOf()))
        val userRoles = user.roles.map { UserRole(it.id, User(id = savedUser.id), it.role) }
        val savedRoles = userRoleService.saveUserRoles(userRoles)

        return savedUser.copy(roles = savedRoles)
    }

    override fun findByUsername(username: String): List<User> = userRepository.findByUsername(username)
}