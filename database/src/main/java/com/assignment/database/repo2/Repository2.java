package com.assignment.database.repo2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.database.model2.users2;


public interface Repository2 extends JpaRepository<users2, String> {

	List<users2> findByPatientname(String patientname);

}