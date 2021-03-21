package br.com.rotaractsorocabaleste.rotasales.core.vo

import java.time.ZonedDateTime

data class ErrorDetailsVO(
    val time: ZonedDateTime = ZonedDateTime.now(),
    val error: String?
) {
    constructor(e: Exception) : this(error = e.message)
}