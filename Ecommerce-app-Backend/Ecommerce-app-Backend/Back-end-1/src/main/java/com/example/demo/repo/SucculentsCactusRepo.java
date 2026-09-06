package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SucculentsCactus;

@Repository
public interface SucculentsCactusRepo extends JpaRepository<SucculentsCactus, Long>{

}
