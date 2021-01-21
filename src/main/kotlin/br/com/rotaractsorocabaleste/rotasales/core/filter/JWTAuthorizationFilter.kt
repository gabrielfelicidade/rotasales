package br.com.rotaractsorocabaleste.rotasales.core.filter

import br.com.rotaractsorocabaleste.rotasales.core.utils.JWTUtils
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
            val auth = getAuthentication(authorizationHeader)
            SecurityContextHolder.getContext().authentication = auth
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