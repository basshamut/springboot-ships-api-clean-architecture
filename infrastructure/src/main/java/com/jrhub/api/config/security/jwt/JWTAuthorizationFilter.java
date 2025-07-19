package com.jrhub.api.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrhub.api.exception.ServiceException;
import com.jrhub.api.dto.HttpErrorInfoDto;
import com.jrhub.api.utils.FormatUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import static com.jrhub.api.utils.Constants.HEADER_AUTHORIZACION_KEY;
import static com.jrhub.api.utils.Constants.SUPER_SECRET_KEY;
import static com.jrhub.api.utils.Constants.TOKEN_BEARER_PREFIX;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        final String header = req.getHeader(HEADER_AUTHORIZACION_KEY);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        final UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (ServiceException ex) {
            final ObjectMapper mapper = new ObjectMapper();
            final HttpErrorInfoDto httpErrorInfoDto = FormatUtils.httpErrorInfoFormatted(HttpStatus.UNAUTHORIZED, req, ex);

            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.getWriter().write(mapper.writeValueAsString(httpErrorInfoDto));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZACION_KEY);

        if (token == null || token.trim().isEmpty()) {
            throw new ServiceException("Authentication failed: Authorization header is missing", 401);
        }

        if (!token.startsWith(TOKEN_BEARER_PREFIX)) {
            throw new ServiceException("Authentication failed: Invalid token format. Expected Bearer token", 401);
        }

        String cleanToken = token.replace(TOKEN_BEARER_PREFIX, "").trim();

        if (cleanToken.isEmpty()) {
            throw new ServiceException("Authentication failed: Token is empty after Bearer prefix", 401);
        }

        try {
            String user = Jwts.parserBuilder()
                    .setSigningKey(SUPER_SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(cleanToken)
                    .getBody()
                    .getSubject();

            if (user == null || user.trim().isEmpty()) {
                throw new ServiceException("Authentication failed: Token does not contain valid user information", 401);
            }

            return new UsernamePasswordAuthenticationToken(user.trim(), null, new ArrayList<>());

        } catch (ExpiredJwtException e) {
            throw new ServiceException("Authentication failed: Token has expired", 401);
        } catch (UnsupportedJwtException e) {
            throw new ServiceException("Authentication failed: Token format is not supported", 401);
        } catch (MalformedJwtException e) {
            throw new ServiceException("Authentication failed: Token is malformed", 401);
        } catch (SecurityException e) {
            throw new ServiceException("Authentication failed: Token security validation failed", 401);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Authentication failed: Token argument is invalid", 401);
        } catch (Exception e) {
            log.error("Authentication failed: An unexpected error occurred while processing the token", e);
            throw new ServiceException("Authentication failed: Unable to process token", 401);
        }
    }
}
