package com.My.Spring_Final_Project.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("timestamp", new Date());
        body.put("error", "Unauthorized");
        body.put("message", getCustomMessage(authException, request));
        body.put("path", request.getServletPath());
        body.put("method", request.getMethod());

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }

    private String getCustomMessage(AuthenticationException authException, HttpServletRequest request) {
        String message = authException.getMessage();

        if (message != null && (message.contains("JWT") || message.contains("token"))) {

            String header = request.getHeader("Authorization");

            if (header == null)
                return "Missing Authorization header";

            if (!header.startsWith("Bearer "))
                return "Invalid Authorization header format. Expected 'Bearer <token>'";

            return "Invalid or expired JWT token";
        }

        return "Authentication failed: " + message;
    }
}

