package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OutdoarPlants;

import jakarta.transaction.Transactional;

@Repository
public interface OutdoarPlantsRepo extends JpaRepository<OutdoarPlants, Long> {

	@Modifying
    @Transactional
    @Query("UPDATE OutdoarPlants p SET p.qty = 0")
    void resetAllQty();
}
