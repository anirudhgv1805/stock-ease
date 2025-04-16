package com.anirudhgv.stockease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anirudhgv.stockease.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
