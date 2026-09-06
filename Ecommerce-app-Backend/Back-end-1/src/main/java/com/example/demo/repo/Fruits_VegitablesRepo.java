package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Fruits_Vegitables;

import jakarta.transaction.Transactional;

@Repository
public interface Fruits_VegitablesRepo extends JpaRepository<Fruits_Vegitables, Long>{
	@Modifying
	@Transactional
	@Query("UPDATE Fruits_Vegitables p SET p.qty = 0")
	void resetAllQty();

}

