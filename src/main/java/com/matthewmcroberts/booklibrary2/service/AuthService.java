package com.matthewmcroberts.booklibrary2.service;

import com.matthewmcroberts.booklibrary2.dto.AuthResponseDto;
import com.matthewmcroberts.booklibrary2.exception.EmailAlreadyExistsException;
import com.matthewmcroberts.booklibrary2.exception.UsernameAlreadyExistsException;
import com.matthewmcroberts.booklibrary2.model.User;
import com.matthewmcroberts.booklibrary2.repository.UserRepository;
import com.matthewmcroberts.booklibrary2.role.Role;
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

    public void registerUser(@NonNull final String username, @NonNull final String password) {
        final Optional<User> usernameOpt = userRepository.findByUsername(username);
        if (usernameOpt.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        final User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public AuthResponseDto login(@NonNull final String username, @NonNull final String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return AuthResponseDto.builder()
                .token(jwtService.generateToken(userDetails))
                .build();
    }
}
