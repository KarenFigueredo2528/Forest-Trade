package com.unbosque.edu.co.forest.model.repository.impl;

import com.unbosque.edu.co.forest.model.entity.User;
import com.unbosque.edu.co.forest.model.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }
    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT u FROM User u WHERE u.email = :email";
        try {
            User user = entityManager.createQuery(query, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
