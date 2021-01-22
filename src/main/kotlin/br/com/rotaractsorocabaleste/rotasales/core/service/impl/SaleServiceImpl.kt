package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleItem
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleItemService
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleService
import br.com.rotaractsorocabaleste.rotasales.core.vo.SaleRequestVO
import br.com.rotaractsorocabaleste.rotasales.core.vo.toSale
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.SaleRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class SaleServiceImpl(
    private val saleRepository: SaleRepository,
    private val saleItemService: SaleItemService
) : SaleService {
    override fun create(saleRequestVO: SaleRequestVO): Sale {
        val sale = saleRepository.save(saleRequestVO.toSale())

        saleItemService.saveSaleItems(getSaleItemsForSaleRequestVO(saleRequestVO))

        return sale
    }

    override fun update(saleRequestVO: SaleRequestVO): Sale? =
        if (checkSaleAlreadyExists(saleRequestVO.id)) {
            val oldSaleItems = saleItemService.getSaleItemsBySaleId(saleRequestVO.id)
            val newSaleItems = getSaleItemsForSaleRequestVO(saleRequestVO)

            saleItemService.removeSaleItems(getPendingRemoveSaleItems(oldSaleItems, newSaleItems))

            saleItemService.saveSaleItems(newSaleItems)

            saleRequestVO.toSale()
        } else
            throw Exception("Bad Request")

    override fun delete(saleId: UUID): Boolean {
        val sale = saleRepository.findById(saleId).get().copy(active = false)

        saleRepository.save(sale)

        return true
    }

    override fun getSalesBySellerId(sellerId: UUID): List<SaleRequestVO> =
        saleRepository.findBySellerIdAndActiveTrue(sellerId = sellerId)
            .parallelStream()
            .map {
                SaleRequestVO(
                    customer = it.customer,
                    event = it.event,
                    id = it.id,
                    items = saleItemService.getSaleItemsBySaleId(it.id),
                    seller = it.seller
                )
            }.collect(Collectors.toList())

    private fun getSaleItemsForSaleRequestVO(sale: SaleRequestVO): List<SaleItem> =
        sale.items
            .parallelStream()
            .map {
                SaleItem(
                    id = it.id,
                    sale = Sale(sale.id),
                    item = it.item,
                    amount = it.amount,
                    unitaryValue = it.unitaryValue
                )
            }.collect(Collectors.toList())

    private fun getPendingRemoveSaleItems(oldSaleItems: List<SaleItem>, newSaleItems: List<SaleItem>): List<SaleItem> {
        val newSaleItemsIds = newSaleItems.map { it.id }
        return oldSaleItems
            .parallelStream()
            .filter { it.id !in newSaleItemsIds }
            .collect(Collectors.toList())
    }

    private fun checkSaleAlreadyExists(saleId: UUID): Boolean = saleRepository.existsById(saleId)
}