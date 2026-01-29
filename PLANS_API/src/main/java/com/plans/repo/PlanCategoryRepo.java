package com.plans.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plans.entity.PlanCategory;


public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{

}