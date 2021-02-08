package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.User

interface UserService {
    fun save(user: User): User
    fun findByUsername(username: String): List<User>
    fun getLoggedInUser(): User
}