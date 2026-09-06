package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FloweringPlant;

import jakarta.transaction.Transactional;

@Repository
public interface FloweringPlantsRepo extends JpaRepository<FloweringPlant, Long>{

	@Modifying
	@Transactional
	@Query("UPDATE FloweringPlant p SET p.qty = 0")
	void resetAllQty();
}
