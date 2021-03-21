package br.com.rotaractsorocabaleste.rotasales.core.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "item")
data class Item(
    @Id
    @Column(name = "item_id")
    val id: UUID = UUID.randomUUID(),
    val description: String? = null,
    val value: BigDecimal = BigDecimal.ZERO,
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    val event: Event? = null
)