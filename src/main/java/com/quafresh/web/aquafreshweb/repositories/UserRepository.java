package com.quafresh.web.aquafreshweb.repositories;

import com.quafresh.web.aquafreshweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findAllUsersByRole(@Param("role") boolean role);

    @Query("SELECT u FROM User u WHERE u.role = :role AND LOWER(u.fullname) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> searchUserByFullnameContainingIgnoreCase(@Param("role") boolean role, @Param("name") String name);
}
