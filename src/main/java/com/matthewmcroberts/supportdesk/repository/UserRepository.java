package com.matthewmcroberts.supportdesk.repository;

import com.matthewmcroberts.supportdesk.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull Optional<User> findByEmail(@NonNull String email);
}
