package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "item")
data class Item(
    @Id
    @Column(name = "item_id")
    val id: UUID = UUID.randomUUID(),
    val description: String? = null,
    val value: BigDecimal = BigDecimal.ZERO
)