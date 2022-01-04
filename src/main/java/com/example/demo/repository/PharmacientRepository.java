package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pharmacient;
import com.example.demo.model.User;


@Repository
public interface PharmacientRepository extends JpaRepository<Pharmacient, Long>{
	Pharmacient findByFirstName(String firstName);
	
	@Query(value="select * from pharmacient b where b.address like %:keyword% or b.email like %:keyword% or b.first_name like %:keyword% or b.last_name like %:keyword%", nativeQuery = true)
	List<User> search(@Param("keyword")String a);
	
}
