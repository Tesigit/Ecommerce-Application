package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SoilPesticides;

import jakarta.transaction.Transactional;

@Repository
public interface SoilPesticideRepo extends JpaRepository<SoilPesticides, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE SoilPesticides p SET p.qty = 0")
	void resetAllQty();
}
