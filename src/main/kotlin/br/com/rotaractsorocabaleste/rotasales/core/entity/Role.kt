package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "role")
data class Role(
    @Id
    @Column(name = "role_id")
    val id: UUID = UUID.randomUUID(),
    val description: String? = null
)