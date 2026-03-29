package com.glop.cibl_orga_sport.metrics;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class AdminUsageMetricsFilter extends OncePerRequestFilter {

    private static final Pattern NUMERIC_ID = Pattern.compile("/\\d+");
    private static final Pattern UUID_ID = Pattern.compile("/[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

    private final AdminUsageMetricsService metricsService;

    public AdminUsageMetricsFilter(AdminUsageMetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long startNanos = System.nanoTime();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long durationNanos = System.nanoTime() - startNanos;
            String uri = request.getRequestURI();
            String method = request.getMethod();
            int status = response.getStatus();

            if (status < 400 && "POST".equalsIgnoreCase(method) && "/api/v1/auth/signin".equals(uri)) {
                metricsService.recordSigninDuration(durationNanos);
            }

            if (status < 400 && "GET".equalsIgnoreCase(method) && uri.startsWith("/api/") && !uri.startsWith("/api/v1/auth")) {
                metricsService.recordDataConsultation(normalizeEndpoint(uri));
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String authorizationHeader = request.getHeader("Authorization");
            boolean hasBearerToken = authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
            if (status < 400 && (isAuthenticated(authentication) || hasBearerToken)) {
                metricsService.recordAuthenticatedUsage(durationNanos);
            }
        }
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    private String normalizeEndpoint(String uri) {
        String normalized = NUMERIC_ID.matcher(uri).replaceAll("/{id}");
        normalized = UUID_ID.matcher(normalized).replaceAll("/{uuid}");
        return normalized;
    }
}
