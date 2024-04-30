package com.example.suyash_socialmedia.repository;

//package com.example.socialmedia.repository;

import com.example.suyash_socialmedia.model.userp.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User1, Integer> {
    // You can add custom query methods here if needed
    User1 findByEmail(String email);
    boolean existsByEmail(String email);
}
