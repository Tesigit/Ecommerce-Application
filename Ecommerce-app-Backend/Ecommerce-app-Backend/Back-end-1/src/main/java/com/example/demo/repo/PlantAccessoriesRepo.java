package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PlantAccessories;

@Repository
public interface PlantAccessoriesRepo extends JpaRepository<PlantAccessories, Long>{

}
