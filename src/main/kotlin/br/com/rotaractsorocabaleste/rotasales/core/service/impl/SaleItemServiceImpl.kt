package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleItem
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleItemService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.SaleItemRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SaleItemServiceImpl(
    private val saleItemRepository: SaleItemRepository
) : SaleItemService {
    override fun saveSaleItems(saleItems: List<SaleItem>): List<SaleItem> =
        saleItemRepository
            .saveAll(saleItems)
            .toMutableList()

    override fun getSaleItemsBySaleId(saleId: UUID): List<SaleItem> =
        saleItemRepository.findBySaleId(saleId)

    override fun removeSaleItems(saleItems: List<SaleItem>) =
        saleItemRepository.deleteAll(saleItems)

}