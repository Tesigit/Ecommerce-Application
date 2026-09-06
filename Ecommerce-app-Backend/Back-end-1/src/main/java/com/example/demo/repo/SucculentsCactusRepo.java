package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SucculentsCactus;

import jakarta.transaction.Transactional;

@Repository
public interface SucculentsCactusRepo extends JpaRepository<SucculentsCactus, Long>{
	@Modifying
	@Transactional
	@Query("UPDATE SucculentsCactus p SET p.qty = 0")
	void resetAllQty();
}
