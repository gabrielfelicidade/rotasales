package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
data class SaleItem(
    @Id
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(nullable = false)
    val sale: Sale? = null,
    @ManyToOne
    @JoinColumn(nullable = false)
    val item: Item? = null,
    @Column(nullable = false)
    val amount: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false)
    val unitaryValue: BigDecimal = BigDecimal.ZERO
)