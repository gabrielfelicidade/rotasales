package br.com.rotaractsorocabaleste.rotasales.core.vo

import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleStatus
import java.util.*

data class PatchSaleStatusRequestVO(
    val saleId: UUID,
    val status: SaleStatus
)