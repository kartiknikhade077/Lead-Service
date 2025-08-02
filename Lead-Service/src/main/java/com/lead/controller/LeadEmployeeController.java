package com.lead.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.client.UserSerivceClinet;
import com.lead.dto.Company;
import com.lead.dto.Employee;
import com.lead.dto.LeadWithColumnsDTO;
import com.lead.entity.Lead;
import com.lead.entity.LeadColumn;
import com.lead.repository.LeadColumnRepository;
import com.lead.repository.LeadRepository;

@RestController
@RequestMapping("/lead/employee")
public class LeadEmployeeController {
	
	@Autowired
	private UserSerivceClinet userSerivceClinet;
    
	@Autowired
	private LeadRepository leadRepository;
	
	@Autowired
	private LeadColumnRepository leadColumnRepository;
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	Employee employee;
    Company company;
    
	@ModelAttribute
	public Employee employeeInfo() {

		employee = userSerivceClinet.getEmployeeInfo();
		
		return employee;

	}
	
	@GetMapping("/testing")
	public String test(){
		
		return "Testing";
		
	}
	
	
	
	@PostMapping("/createLead")
	public Lead createLead(@RequestBody LeadWithColumnsDTO dto) {
		
		Lead lead = new Lead();
		lead.setFields(dto.getLead());
		lead.setCompanyId(employee.getCompanyId());
		lead.setEmployeeId(employee.getEmployeeId());
		lead.setCreatedDate(LocalDateTime.now());
		lead.setUpdatedDate(LocalDateTime.now());
		lead.setStatus("New Lead");
	//	lead.setSource(dto.getSource());
		lead.setAssignTo(dto.getAssignTo());
		leadRepository.save(lead);

		return lead;
	}
	
	
	@GetMapping("/getAllLeads/{page}/{size}")
	public ResponseEntity<?> getAllLeads(@PathVariable int page ,@PathVariable int size,@RequestParam(defaultValue = "") String name) {

	    try {
	        // 1. Fetch column metadata for company
	        LeadColumn leadColumn = leadColumnRepository.findByCompanyId(employee.getCompanyId());
	        List<LeadColumn.ColumnDefinition> sortedColumns = leadColumn.getColumns()
	                .stream()
	                .sorted(Comparator.comparingInt(LeadColumn.ColumnDefinition::getSequence))
	                .toList();

	        // 2. Fetch paginated leads for company
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Lead> leadPage =null;
	        if(name.isEmpty()) {
	         leadPage = leadRepository.findByEmployeeIdOrderByIdDesc(employee.getEmployeeId(), pageable);
	        }else {
				// Build dynamic OR criteria for all columns
				List<Criteria> fieldCriteria = new ArrayList<>();
				for (LeadColumn.ColumnDefinition colDef : sortedColumns) {
					fieldCriteria.add(Criteria.where("fields." + colDef.getName()).regex(name, "i"));
				}

				Criteria criteria = new Criteria().andOperator(Criteria.where("companyId").is(employee.getCompanyId()),
						new Criteria().orOperator(fieldCriteria.toArray(new Criteria[0])));

				Query query = new Query(criteria).with(pageable);
				List<Lead> leads = mongoTemplate.find(query, Lead.class);

				// Get total count for pagination
				long total = mongoTemplate.count(new Query(criteria), Lead.class);

				leadPage = new PageImpl<>(leads, pageable, total);
	        }

	        // 3. Prepare response
	        Map<String, Object> response = new HashMap<>();
	        response.put("columnSequence", sortedColumns);
	        response.put("leadList", leadPage.getContent());
	        response.put("currentPage", leadPage.getNumber());
	      //  response.put("totalItems", leadPage.getTotalElements());
	        response.put("totalPages", leadPage.getTotalPages());

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error " + e.getMessage());
	    }
	}
	
	@GetMapping("/getLeadById/{leadId}")
	public ResponseEntity<?> getLeadById(@PathVariable String leadId) {
		try {
			
			Map<String,Object> leadInfo=new HashMap<>();
			
			LeadColumn leadColumn = leadColumnRepository.findByCompanyId(employee.getCompanyId());
			 List<LeadColumn.ColumnDefinition> sortedColumns = leadColumn.getColumns()
				        .stream()
				        .sorted(Comparator.comparingInt(LeadColumn.ColumnDefinition::getSequence))
				        .toList();
			Optional<Lead> lead = leadRepository.findById(leadId);
			
			leadInfo.put("lead",lead);
			leadInfo.put("leadColumn", sortedColumns);
			
			return ResponseEntity.ok(leadInfo);
		} catch (Exception e) {

			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());

		}
	}
	
	
	@PutMapping("/updateLead/{id}")
	public ResponseEntity<?> updateLead(@PathVariable String id, @RequestBody LeadWithColumnsDTO dto) {
		try {
			Map<String,Object> leadInfo=new HashMap<>();
			
			Lead lead = leadRepository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found"));

			lead.setFields(dto.getLead());
			
			leadRepository.save(lead);
			
			leadInfo.put("lead",lead);
			
			return ResponseEntity.ok(leadInfo);
		} catch (Exception e) {

			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}
	}
	
	@PutMapping("/updateLeadStatus/{leadId}/{leadStatus}")
	public ResponseEntity<?> updateLeadStatus(@PathVariable String leadId, @PathVariable String leadStatus) {

		try {

			Lead lead = leadRepository.findByIdAndCompanyId(leadId, employee.getCompanyId());
			lead.setStatus(leadStatus);
			leadRepository.save(lead);
			return ResponseEntity.ok("Lead Status Updated");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}
	}

}
