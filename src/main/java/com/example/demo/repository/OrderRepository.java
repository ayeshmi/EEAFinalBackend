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
	@Query(value="select * from order_Table where user_ids=:keyword AND type='order'", nativeQuery = true)
	List<Order> search(@Param("keyword")Long userId);
	
	@Query(value="select * from order_Table where user_ids=:keyword AND type='cart'", nativeQuery = true)
	List<Order> searchCartDetails(@Param("keyword")Long a);
	
	@Query(value="select * from order_Table where user_ids=:keyword AND type='order' AND status='processing'", nativeQuery = true)
	List<Order> searchProcessOrderDetails(@Param("keyword")Long a);
	
	@Query(value="select * from order_Table where user_ids=:keyword AND type='order' AND status='Cancel'", nativeQuery = true)
	List<Order> searchCancelOrderDetails(@Param("keyword")Long a);
	
	@Query(value="select * from order_Table where user_ids=:keyword AND type='order' AND status='Completed'", nativeQuery = true)
	List<Order> searchComletedOrderDetails(@Param("keyword")Long a);
	
	@Query(value="select * from order_Table where type='order' AND status='Cancel'", nativeQuery = true)
	List<Order> getCancelOrders();
	
	@Query(value="select * from order_Table where type='order' AND status='Completed'", nativeQuery = true)
	List<Order> getCompletedOrders();
	
	@Query(value="select * from order_Table where type='order' AND status='processing'", nativeQuery = true)
	List<Order> getProcessingOrders();
	
	@Query(value="select *  from order_table where date  in (select  DISTINCT date FROM order_table where type='order' and status='processing' ) GROUP BY date;", nativeQuery = true)
	List<Order> viewOrders();
	
	
	
}
