package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.vo.SaleRequestVO
import java.util.*

interface SaleService {
    fun create(saleRequestVO: SaleRequestVO): Sale
    fun update(saleRequestVO: SaleRequestVO): Sale?
    fun delete(saleId: UUID): Boolean
    fun getSalesBySellerId(sellerId: UUID): List<SaleRequestVO>
}