package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleItem
import br.com.rotaractsorocabaleste.rotasales.core.exception.EntityNotFoundException
import br.com.rotaractsorocabaleste.rotasales.core.exception.UserUnauthorizedException
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleItemService
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleService
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import br.com.rotaractsorocabaleste.rotasales.core.vo.PatchSaleStatusRequestVO
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.SaleRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class SaleServiceImpl(
    private val saleRepository: SaleRepository,
    private val saleItemService: SaleItemService,
    private val userService: UserService
) : SaleService {
    override fun create(sale: Sale) {
        saleRepository.save(
            sale.copy(
                seller = userService.getLoggedInUser(),
                items = listOf()
            )
        )

        saleItemService.saveSaleItems(
            sale.items.map {
                it.copy(
                    sale = Sale(
                        id = sale.id
                    )
                )
            }
        )
    }

    override fun update(sale: Sale) {
        getExistingSale(sale.id)

        val oldSaleItems = saleItemService.getSaleItemsBySaleId(sale.id)
        val newSaleItems = getSaleItemsForSale(sale)

        saleItemService.removeSaleItems(getPendingRemoveSaleItems(oldSaleItems, newSaleItems))

        saleItemService.saveSaleItems(newSaleItems)
    }

    override fun delete(saleId: UUID) {
        val sale = getExistingSale(saleId)

        checkSaleBelongsToLoggedInUser(sale)

        saleRepository.save(
            sale.copy(
                active = false
            )
        )
    }

    override fun getLoggedInUserSales(): List<Sale> =
        saleRepository.findBySellerIdAndActiveTrue(sellerId = userService.getLoggedInUser().id)

    override fun patchSaleStatus(patchSaleStatusRequestVO: PatchSaleStatusRequestVO) {
        saleRepository.save(
            getExistingSale(patchSaleStatusRequestVO.saleId)
                .copy(
                    status = patchSaleStatusRequestVO.status
                )
        )
    }

    private fun getSaleItemsForSale(sale: Sale): List<SaleItem> =
        sale.items
            .parallelStream()
            .map {
                SaleItem(
                    id = it.id,
                    sale = Sale(sale.id),
                    item = it.item,
                    amount = it.amount
                )
            }.collect(Collectors.toList())

    private fun getPendingRemoveSaleItems(oldSaleItems: List<SaleItem>, newSaleItems: List<SaleItem>): List<SaleItem> {
        val newSaleItemsIds = newSaleItems.map { it.id }

        return oldSaleItems
            .parallelStream()
            .filter { it.id !in newSaleItemsIds }
            .collect(Collectors.toList())
    }

    private fun getExistingSale(saleId: UUID): Sale = saleRepository.findById(saleId).let {
        if (it.isEmpty)
            throw EntityNotFoundException(saleId.toString())
        else
            it.get()
    }

    private fun checkSaleBelongsToLoggedInUser(sale: Sale) {
        if (sale.seller!!.id.toString() != userService.getLoggedInUser().id.toString())
            throw UserUnauthorizedException(userService.getLoggedInUser().id)
    }
}