package com.indrasom.user.service.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indrasom.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
