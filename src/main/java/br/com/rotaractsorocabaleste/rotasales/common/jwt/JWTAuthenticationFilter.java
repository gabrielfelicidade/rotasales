package br.com.rotaractsorocabaleste.rotasales.common.jwt;

import br.com.rotaractsorocabaleste.rotasales.user.User;
import br.com.rotaractsorocabaleste.rotasales.user.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static br.com.rotaractsorocabaleste.rotasales.common.jwt.SecurityConstants.EXPIRATION_TIME;
import static br.com.rotaractsorocabaleste.rotasales.common.jwt.SecurityConstants.SECRET;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/auth");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest req,
                                                final HttpServletResponse res) throws AuthenticationException {
        try {
            final var creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest req,
                                            final HttpServletResponse res,
                                            final FilterChain chain,
                                            final Authentication auth) throws IOException {
        final var objectMapper = new ObjectMapper();
        final var token = JWT.create()
                .withSubject(((UserDetailsImpl) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        final var response = Map.of("access_token", token);

        res.setContentType("application/json");
        res.getWriter().write(objectMapper.writeValueAsString(response));
        res.getWriter().flush();
    }
}
