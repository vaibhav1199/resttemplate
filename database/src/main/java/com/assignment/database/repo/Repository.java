package com.assignment.database.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.database.model.users;


public interface Repository extends JpaRepository<users, String> {

	List<users> findByUsername(String username);

}