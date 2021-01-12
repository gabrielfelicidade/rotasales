package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {
    override fun save(user: User): User = userRepository.save(user)
}