package com.dinesh.jpa.jpasamples.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinesh.jpa.jpasamples.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
