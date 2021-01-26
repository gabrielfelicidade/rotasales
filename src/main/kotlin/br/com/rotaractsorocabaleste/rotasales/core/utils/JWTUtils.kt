package br.com.rotaractsorocabaleste.rotasales.core.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class JWTUtils {

    companion object {
        private const val expiration: Long = 3600000
        private val secret = Keys.secretKeyFor(SignatureAlgorithm.HS512)

        fun generateToken(username: String): String = Jwts
            .builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secret)
            .compact()

        fun isTokenValid(token: String): Boolean {
            val claims = getClaimsToken(token)
            val expirationDate = claims.expiration

            return ZonedDateTime
                .now()
                .toInstant()
                .isBefore(expirationDate.toInstant())
        }

        fun getUsername(token: String): String = getClaimsToken(token).subject

        private fun getClaimsToken(token: String): Claims = Jwts
            .parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body
    }
}