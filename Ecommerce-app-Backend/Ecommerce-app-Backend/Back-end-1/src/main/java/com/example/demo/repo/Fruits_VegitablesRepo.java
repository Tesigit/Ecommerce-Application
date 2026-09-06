package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Fruits_Vegitables;

@Repository
public interface Fruits_VegitablesRepo extends JpaRepository<Fruits_Vegitables, Long>{

}

