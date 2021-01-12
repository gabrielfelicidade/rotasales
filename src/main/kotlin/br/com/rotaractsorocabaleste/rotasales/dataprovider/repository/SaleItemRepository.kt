package br.com.rotaractsorocabaleste.rotasales.dataprovider.repository

import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleItem
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SaleItemRepository : CrudRepository<SaleItem, UUID> {
    fun findBySaleId(saleId: UUID): List<SaleItem>
}