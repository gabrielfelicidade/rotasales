package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Sale(
        @Id
        val id: UUID = UUID.randomUUID(),
        @ManyToOne
        val seller: User? = null,
        val customer: String? = null,
        @ManyToOne
        val event: Event? = null,
        val active: Boolean = true
)