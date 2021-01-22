package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Role(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val description: String? = null
)