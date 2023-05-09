package com.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.models.User;

public interface UserRepository extends JpaRepository<User, String>{

}
