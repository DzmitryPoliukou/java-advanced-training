package com.epam.java_advanced_training.secutiry.config;

import com.epam.java_advanced_training.secutiry.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtService jwtTokenService;

  public OAuth2LoginSuccessHandler(JwtService jwtTokenService) {
    this.jwtTokenService = jwtTokenService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    String jwtToken = jwtTokenService.generateToken(oAuth2User);

    response.setContentType("application/json");
    response.getWriter().write("{\"token\": \"" + jwtToken + "\"}");
    response.getWriter().flush();
  }

}
