package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Attendence;


public interface AttendenceRepository extends JpaRepository<Attendence,Long> , JpaSpecificationExecutor<Attendence>{

List<Attendence> findByDate(String date);
Attendence findByUsername(String username);
	
}
