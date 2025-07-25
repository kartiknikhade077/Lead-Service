package com.lead.filter;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExternalTokenValidationFilter extends OncePerRequestFilter {

    @Value("${auth.service.url}")
    private String authServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(7);

        // Decode token payload
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token format");
            return;
        }

        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> claims = mapper.readValue(payloadJson, Map.class);

        String userName = (String) claims.get("sub");
        if (userName == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Token does not contain subject");
            return;
        }

        try {
            // Safer call than getForObject() — does not throw automatically on non-2xx
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> authResponse = restTemplate.exchange(
                    authServiceUrl + "?token=" + token,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (authResponse.getStatusCode() != HttpStatus.OK) {
            	
                sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "Token validation failed");
                return;
            }

            // Wrap request to inject userName into header
            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getHeader(String name) {
                    if ("userName".equalsIgnoreCase(name)) {
                        return userName;
                    }
                    return super.getHeader(name);
                }

                @Override
                public Enumeration<String> getHeaderNames() {
                    List<String> headerNames = Collections.list(super.getHeaderNames());
                    if (!headerNames.contains("userName")) {
                        headerNames.add("userName");
                    }
                    return Collections.enumeration(headerNames);
                }

                @Override
                public Enumeration<String> getHeaders(String name) {
                    if ("userName".equalsIgnoreCase(name)) {
                        return Collections.enumeration(List.of(userName));
                    }
                    return super.getHeaders(name);
                }
            };

            filterChain.doFilter(wrappedRequest, response);

        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Token validation error: " + e.getMessage());
        }
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> error = new HashMap<>();
        error.put("status", status);
        error.put("error", status == 401 ? "Unauthorized" : "Forbidden");
        error.put("message", message);

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(error));
        response.getWriter().flush();
    }
}
