package br.com.rotaractsorocabaleste.rotasales.core.filter

import br.com.rotaractsorocabaleste.rotasales.core.utils.JWTUtils
import io.jsonwebtoken.security.SignatureException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(
    authenticationManager: AuthenticationManager
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader?.startsWith("Bearer") == true) {
            try {
                val auth = getAuthentication(authorizationHeader)
                SecurityContextHolder.getContext().authentication = auth
            } catch (e: SignatureException) {
                response.contentType = "application/json"
                response.characterEncoding = "UTF-8"
                response.writer.write("{\"error\": \"Invalid token. Please refresh token.\"}")
            }
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(authorizationHeader: String?): UsernamePasswordAuthenticationToken {
        val token = authorizationHeader?.substring(7) ?: ""
        if (JWTUtils.isTokenValid(token)) {
            val username = JWTUtils.getUsername(token)
            return UsernamePasswordAuthenticationToken(username, null, mutableListOf())
        }
        throw UsernameNotFoundException("Auth invalid!")
    }

}