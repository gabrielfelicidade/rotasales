package br.com.rotaractsorocabaleste.rotasales.core.exception

class BadRequestException(
    error: ExceptionEnum,
    vararg args: String
) : GenericException(error.reason, error.message.format(*args))