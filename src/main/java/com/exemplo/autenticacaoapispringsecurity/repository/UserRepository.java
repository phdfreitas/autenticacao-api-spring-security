package com.exemplo.autenticacaoapispringsecurity.repository;

import com.exemplo.autenticacaoapispringsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select e from User e join fetch e.roles where e.username=(:username)")
    public User findByUsername(@Param("username") String username);

}
