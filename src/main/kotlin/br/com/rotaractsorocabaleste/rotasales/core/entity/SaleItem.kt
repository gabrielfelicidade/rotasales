package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class SaleItem(
        @Id
        val id: UUID = UUID.randomUUID(),
        @ManyToOne
        val sale: Sale? = null,
        @ManyToOne
        val item: Item? = null,
        val amount: BigDecimal = BigDecimal.ZERO,
        val unitaryValue: BigDecimal = BigDecimal.ZERO
)