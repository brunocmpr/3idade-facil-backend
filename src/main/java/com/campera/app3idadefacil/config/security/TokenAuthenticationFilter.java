package com.campera.app3idadefacil.config.security;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.repository.AppUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private AppUserRepository appUserRepository;

    public TokenAuthenticationFilter(TokenService tokenService, AppUserRepository appUserRepository) {
        super();
        this.tokenService = tokenService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = extractTokenFromHttpRequest(request);
        boolean tokenValid = tokenService.isTokenValid(token);
        if(tokenValid) {
            authenticateClient(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        Long userId = tokenService.extractSubjectFromToken(token);
        AppUser user = this.appUserRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken userPwAuthToken = new UsernamePasswordAuthenticationToken(
                user, null , user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userPwAuthToken);
    }

    private String extractTokenFromHttpRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || ! token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
