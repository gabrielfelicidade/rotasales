package br.com.rotaractsorocabaleste.rotasales.core.vo

import br.com.rotaractsorocabaleste.rotasales.core.exception.GenericException
import java.time.ZonedDateTime

data class ErrorDetailsVO(
    val time: ZonedDateTime = ZonedDateTime.now(),
    val reason: String
) {
    constructor(e: GenericException) : this(reason = e.reason)
}