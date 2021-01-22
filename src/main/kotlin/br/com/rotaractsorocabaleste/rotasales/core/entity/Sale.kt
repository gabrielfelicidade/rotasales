package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.util.*
import javax.persistence.*

@Entity
data class Sale(
    @Id
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(nullable = false)
    val seller: User? = null,
    @Column(nullable = false)
    val customer: String? = null,
    @ManyToOne
    @JoinColumn(nullable = false)
    val event: Event? = null,
    @Column(nullable = false)
    val active: Boolean = true
)