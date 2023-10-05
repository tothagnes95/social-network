package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);
    boolean existsUserByPassword(String password);
    boolean existsUserByEmail(String email);

    Optional<User> findByUsername(String username);
}
