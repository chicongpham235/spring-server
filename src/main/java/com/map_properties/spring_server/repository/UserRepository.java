package com.map_properties.spring_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.map_properties.spring_server.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    // @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id")
    // Optional<User> findByIdWithRoles(@Param("id") Long id);

}
