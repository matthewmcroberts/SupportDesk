package com.matthewmcroberts.booklibrary2.repository;

import com.matthewmcroberts.booklibrary2.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull Optional<User> findByUsername(@NonNull String username);
}
