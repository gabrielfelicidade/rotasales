package br.com.rotaractsorocabaleste.rotasales.core.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sale_item")
data class SaleItem(
    @Id
    @Column(name = "sale_item_id")
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonIgnore
    val sale: Sale? = null,
    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: Item? = null,
    val amount: BigDecimal = BigDecimal.ZERO,
    val unitaryValue: BigDecimal = BigDecimal.ZERO
)