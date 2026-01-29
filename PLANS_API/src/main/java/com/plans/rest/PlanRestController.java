package com.plans.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.plans.entity.Plan;
import com.plans.properties.AppProperties;
import com.plans.service.PlanService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class PlanRestController {
	

	private PlanService planService;
	
	private Map<String, String> messages;
	
	public PlanRestController(PlanService planService, AppProperties appProps) {
		this.planService = planService;
		this.messages = appProps.getMessages();
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories(){
		Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		
		String responseMsg = "";
		boolean isSaved = planService.savePlan(plan);
//		Map<String, String> messages = appProps.getMessages();
		
		if(isSaved) {
			responseMsg = messages.get("PlanSaveSucc");
		
		}else {
			responseMsg = messages.get("PlanSaveFail");
		}
		
		return new ResponseEntity<>(responseMsg,HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		boolean isUpdated = planService.updatePlan(plan);
//		Map<String, String> messages = appProps.getMessages();
		String msg = "";
		if(isUpdated) {
			msg = messages.get("planUpdateSucc");
		}else {
			msg = messages.get("planUpdateFail");
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		boolean isDeleted = planService.deletePlanById(planId);
//		Map<String, String> messages = appProps.getMessages();
		String msg = "";
		if(isDeleted) {
			msg = messages.get("planDeleteSucc");
		}else {
			msg = messages.get("planDeleteFail");
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{activeSw}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String activeSw){
		
		String msg = "";
		boolean isStatusChanged = planService.planStatusChange(planId, activeSw);
//		Map<String, String> messages = appProps.getMessages();
		
		if(isStatusChanged) {
			msg = messages.get("planStatusChange");
		}else {
			msg = messages.get("planStatusChangeFail");
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
}