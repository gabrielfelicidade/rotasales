package br.com.rotaractsorocabaleste.rotasales.core.exception

import java.util.*

class UserUnauthorizedException(
    userId: UUID?
) : Exception(
    "User requested an action be taken on a resource that " +
            "doesn't have permission. UserId=$userId"
)