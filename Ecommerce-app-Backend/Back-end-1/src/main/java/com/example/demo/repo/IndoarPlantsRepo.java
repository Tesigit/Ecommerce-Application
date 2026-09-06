package com.example.demo.repo;

import java.beans.Transient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.IndoarPlant;


@Repository
public interface IndoarPlantsRepo extends JpaRepository<IndoarPlant, Integer>{

	@Modifying
    @Transient
    @Query("UPDATE IndoarPlant p SET p.qty = 0")
    void resetAllQty();
}



