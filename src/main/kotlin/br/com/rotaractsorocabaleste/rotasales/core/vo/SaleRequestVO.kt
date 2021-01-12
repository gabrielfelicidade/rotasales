package br.com.rotaractsorocabaleste.rotasales.core.vo

import br.com.rotaractsorocabaleste.rotasales.core.entity.Event
import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleItem
import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import java.util.*

data class SaleRequestVO(
        val id: UUID = UUID.randomUUID(),
        val seller: User? = null,
        val customer: String? = null,
        val event: Event? = null,
        val items: List<SaleItem> = listOf()
)

fun SaleRequestVO.toSale() = Sale(
        customer = customer,
        event = event,
        id = id,
        seller = seller
)