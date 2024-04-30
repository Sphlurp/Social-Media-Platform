package com.example.suyash_socialmedia.repository;

import com.example.suyash_socialmedia.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByPostID(int ID);

   // List<Post> findAllByOrderByDateDesc();
   List<Post> findAll(Sort sort);
}
