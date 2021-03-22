package br.com.rotaractsorocabaleste.rotasales.core.exception

class BadRequestException(
    message: String
) : Exception("Bad request! Error message: $message")