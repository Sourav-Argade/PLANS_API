package com.plans.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plans.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

}