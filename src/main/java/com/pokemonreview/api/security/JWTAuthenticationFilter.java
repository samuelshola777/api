package com.pokemonreview.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTGenerator jwtGenerator;
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String token = getJWTFromRequest(request);
      if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
          String username = jwtGenerator.getUsernameFromJWT(token);
          UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
      filterChain.doFilter(request,response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(bearerToken)&& bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7, bearerToken.length());
            }
            return null;
    }
}