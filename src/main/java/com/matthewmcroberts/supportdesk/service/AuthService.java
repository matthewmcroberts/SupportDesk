package com.matthewmcroberts.supportdesk.service;

import com.matthewmcroberts.supportdesk.dto.AuthResponseDto;
import com.matthewmcroberts.supportdesk.exception.EmailAlreadyExistsException;
import com.matthewmcroberts.supportdesk.exception.UsernameAlreadyExistsException;
import com.matthewmcroberts.supportdesk.model.User;
import com.matthewmcroberts.supportdesk.repository.UserRepository;
import com.matthewmcroberts.supportdesk.role.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public void registerUser(@NonNull final String email, @NonNull final String password) {
        final Optional<User> emailOpt = userRepository.findByEmail(email);
        if (emailOpt.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        final User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public AuthResponseDto login(@NonNull final String email, @NonNull final String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        return AuthResponseDto.builder()
                .token(jwtService.generateToken(userDetails))
                .build();
    }
}
