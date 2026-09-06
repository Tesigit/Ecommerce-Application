package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.IndoarPlant;


@Repository
public interface IndoarPlantsRepo extends JpaRepository<IndoarPlant, Integer>{

}



