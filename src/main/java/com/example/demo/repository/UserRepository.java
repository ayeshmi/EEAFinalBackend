package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import com.example.demo.model.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	User findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "SET FOREIGN_KEY_CHECKS = 0", nativeQuery = true)
	void foreigKeyProblem();
	
	@Query(value="select * from users b where b.email like %:keyword% or b.username like %:keyword% or b.address like %:keyword%", nativeQuery = true)
	List<User> search(@Param("keyword")String a);
}
