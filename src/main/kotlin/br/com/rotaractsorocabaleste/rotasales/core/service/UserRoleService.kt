package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.UserRole

interface UserRoleService {
    fun saveUserRoles(userRoles: List<UserRole>): List<UserRole>
}