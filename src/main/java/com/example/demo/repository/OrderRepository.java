package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>{
	List<Order> findByUserId(Long userId);
	List<Order> findByClientEmail(String clientEmail);
	
	//@Query(value="select * from order_Table where user_id=110 AND type='order'")
	@Query(value="select * from order_Table where user_id=110 AND type='order'", nativeQuery = true)
	List<Order> search(@Param("keyword")Long userId);
	
	@Query(value="select * from order_Table where user_id=110 AND type='cart'", nativeQuery = true)
	List<Order> searchCartDetails(@Param("keyword")Long userId);
	
	
}
