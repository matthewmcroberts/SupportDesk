package com.matthewmcroberts.supportdesk.service;

import com.matthewmcroberts.supportdesk.exception.EmailNotFoundException;
import com.matthewmcroberts.supportdesk.model.User;
import com.matthewmcroberts.supportdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull final String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("Email not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
