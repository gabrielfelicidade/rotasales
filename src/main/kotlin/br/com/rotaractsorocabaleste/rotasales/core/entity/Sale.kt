package br.com.rotaractsorocabaleste.rotasales.core.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sale")
data class Sale(
        @Id
        @Column(name = "sale_id")
        val id: UUID = UUID.randomUUID(),
        @OneToOne
        @JoinColumn(name = "seller_id")
        @JsonIgnoreProperties(value = ["fullName", "roles"])
        val seller: User? = null,
        val customer: String? = null,
        @ManyToOne
        @JoinColumn(name = "event_id")
        @JsonIgnoreProperties(value = ["startDate", "endDate"])
        val event: Event? = null,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        val active: Boolean = true,
        val status: SaleStatus = SaleStatus.AWAITING_WITHDRAWAL,
        @OneToMany(mappedBy = "sale")
        val items: List<SaleItem> = listOf(),
        val donation: Boolean = false
)

enum class SaleStatus {
    AWAITING_WITHDRAWAL,
    WITHDRAWN
}