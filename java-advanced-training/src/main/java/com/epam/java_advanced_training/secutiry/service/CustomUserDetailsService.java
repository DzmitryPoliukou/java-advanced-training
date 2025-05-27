package com.epam.java_advanced_training.secutiry.service;

import com.epam.java_advanced_training.entity.User;
import com.epam.java_advanced_training.repository.UserRepository;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userRepository.findByName(name)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getName())
        .password("")
        .authorities(getAuthorities(user))
        .roles(user.getRole().name())
        .build();
  }

  private Collection<? extends GrantedAuthority> getAuthorities(User user) {
    return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
  }
}
