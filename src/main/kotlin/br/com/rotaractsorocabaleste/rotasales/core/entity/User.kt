package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "[User]")
data class User(
        @Id
        val id: UUID = UUID.randomUUID(),
        val username: String? = null,
        val password: String? = null,
        val fullName: String? = null
)