package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Item(
        @Id
        val id: UUID = UUID.randomUUID(),
        val description: String? = null,
        val value: BigDecimal = BigDecimal.ZERO
)