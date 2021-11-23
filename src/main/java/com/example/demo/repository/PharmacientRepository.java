package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pharmacient;


@Repository
public interface PharmacientRepository extends JpaRepository<Pharmacient, Long>{

}
