package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.UserRole
import br.com.rotaractsorocabaleste.rotasales.core.service.UserRoleService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.UserRoleRepository
import org.springframework.stereotype.Service

@Service
class UserRoleServiceImpl(
    private val userRoleRepository: UserRoleRepository
) : UserRoleService {
    override fun saveUserRoles(userRoles: List<UserRole>): List<UserRole> =
        userRoleRepository.saveAll(userRoles).toList()
}