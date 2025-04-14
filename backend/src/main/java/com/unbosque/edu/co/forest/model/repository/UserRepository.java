package com.unbosque.edu.co.forest.model.repository;

import java.util.Optional;
import com.unbosque.edu.co.forest.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByEmail(String email);
}