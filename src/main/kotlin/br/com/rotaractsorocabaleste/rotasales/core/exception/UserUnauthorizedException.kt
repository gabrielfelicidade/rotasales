package br.com.rotaractsorocabaleste.rotasales.core.exception

import java.util.*

class UserUnauthorizedException(
    userId: UUID?
) : GenericException(
    ExceptionEnum.FORBIDDEN_USER_UNAUTHORIZED.reason,
    ExceptionEnum.FORBIDDEN_USER_UNAUTHORIZED.message.format(userId)
)