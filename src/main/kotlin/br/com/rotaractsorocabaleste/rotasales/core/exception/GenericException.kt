package br.com.rotaractsorocabaleste.rotasales.core.exception

open class GenericException(
    val reason: String,
    message: String
) : Exception(message)