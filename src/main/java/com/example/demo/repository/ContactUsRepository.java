package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ContactUs;
import com.example.demo.model.Item;


@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs,Long> , JpaSpecificationExecutor<ContactUs>{
	
	@Query(value="select * from contact_us where user_id=:keyword ", nativeQuery = true)
	List<ContactUs> findByUserID(@Param("keyword")Long id);

}
