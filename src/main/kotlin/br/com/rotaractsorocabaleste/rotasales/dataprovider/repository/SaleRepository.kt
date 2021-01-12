package br.com.rotaractsorocabaleste.rotasales.dataprovider.repository

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SaleRepository : CrudRepository<Sale, UUID> {
    fun findBySellerIdAndActiveTrue(sellerId: UUID): List<Sale>
}