package br.com.rotaractsorocabaleste.rotasales.entrypoint.config

import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import javax.servlet.ServletException

import java.io.IOException

import javax.servlet.FilterChain

import javax.servlet.http.HttpServletResponse

import javax.servlet.http.HttpServletRequest

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import java.lang.RuntimeException
import java.util.*


class JWTAuthenticationFilter(
    private val expiration: Long = 60000,
    private val secret: String
) : UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?
    ): Authentication? {
        return try {
            val credentials: User = ObjectMapper()
                .readValue(req.inputStream, User::class.java)
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    credentials.username,
                    credentials.password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest?,
        res: HttpServletResponse,
        chain: FilterChain?,
        auth: Authentication
    ) {
        val token: String = Jwts.builder()
            .setSubject((auth.principal as User).username)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

}