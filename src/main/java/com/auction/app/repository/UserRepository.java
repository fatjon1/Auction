package com.auction.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auction.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
}
