package com.assignment.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.database.model.users;
import com.assignment.database.model2.users2;
import com.assignment.database.repo.Repository;
import com.assignment.database.repo2.Repository2;

@RestController
@RequestMapping
public class Controller {
	
	@Autowired
	private Repository data;
	@Autowired
	private Repository2 data2;
	
	@PostMapping("/users")
	public String saveUser(@RequestBody users Users) {
		data.save(Users);
		return "Saved Entry";
	}
	@GetMapping("/users/{username}")
	public List<users> getUser(@PathVariable String username){

		return data.findByUsername(username);
	}
	
	@PostMapping("/appointment")
	public String saveappoint(@RequestBody users2 patient) {
		data2.save(patient);
		return "Saved Entry";
	}
	@GetMapping("/appointment/{patientname}")
	public List<users2> getappoint(@PathVariable String patientname){

		return data2.findByPatientname(patientname);
	}
}
