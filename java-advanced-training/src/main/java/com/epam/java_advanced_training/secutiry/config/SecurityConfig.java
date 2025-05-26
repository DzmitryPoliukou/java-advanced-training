package com.epam.java_advanced_training.secutiry.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Value("${spring.security.oauth2.client.registration.github.client-id}")
  private String clientId;

  @Value("${spring.security.oauth2.client.registration.github.client-secret}")
  private String clientSecret;

  private final JwtRequestFilter jwtRequestFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2LoginSuccessHandler successHandler) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/").permitAll()
            .requestMatchers("/secured").hasRole("ADMIN")
            .anyRequest().authenticated())
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Добавляем JwtRequestFilter перед обработкой UsernamePasswordAuthenticationFilter
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll())
        .oauth2Login(httpSecurityOAuth2LoginConfigurer -> {

                  httpSecurityOAuth2LoginConfigurer.successHandler(successHandler);
            }
        )
        .headers(config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .logout(LogoutConfigurer::permitAll)
        .build();
  }

  // TODO взять из аппликейшн прооеперти
  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {

    ClientRegistration registration = ClientRegistration
        .withRegistrationId("github")
        .clientId(clientId)
        .clientSecret(clientSecret)
        .scope("read:user")
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
        .authorizationUri("https://github.com/login/oauth/authorize")
        .tokenUri("https://github.com/login/oauth/access_token")
        .userInfoUri("https://api.github.com/user")
        .userNameAttributeName("login")
        .clientName("GitHub")
        .build();

    return new InMemoryClientRegistrationRepository(registration);
  }

}
