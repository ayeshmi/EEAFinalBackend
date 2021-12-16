package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Item;



@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

	List<Item> findBySpecifications(String specifications);
	
	@Query(value="select * from items where name=:keyword ", nativeQuery = true)
	Item findByName(@Param("keyword")String name);
	
	@Query(value="select * from items b where b.description like %:keyword% or b.how_to_use like %:keyword% or b.ingredients like %:keyword% or b.item_type like %:keyword% or b.name like %:keyword% or b.specifications like %:keyword% or b.suitable_for like %:keyword%", nativeQuery = true)
	List<Item> search(@Param("keyword")String a);
	
	
	Boolean existsByName(String name);
}
