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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.lead.dto.LeadWithColumnsDTO;
import com.lead.dto.User;
import com.lead.entity.Lead;
import com.lead.entity.LeadColumn;
import com.lead.entity.LeadStatus;
import com.lead.repository.LeadColumnRepository;
import com.lead.repository.LeadRepository;
import com.lead.repository.LeadStatusRepository;

@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	private UserSerivceClinet userSerivceClinet;

	

	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	private LeadColumnRepository leadColumnRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private LeadStatusRepository leadStatusRepository;

	Company company;

	@ModelAttribute
	public void companyDetails() {

		company = userSerivceClinet.getCompanyInfo();

	}

	@PostMapping("/createLead")
	public LeadWithColumnsDTO createLead(@RequestBody LeadWithColumnsDTO dto) {
		// 1. Save or update LeadColumn sequence
		LeadColumn leadColumn = leadColumnRepository.findByCompanyId(company.getCompanyId());

		if (leadColumn == null) {
			leadColumn = new LeadColumn();
			leadColumn.setColumns(dto.getColumns());

		} else {
			leadColumn.setColumns(dto.getColumns());
		}
		
		leadColumn.setCompanyId(company.getCompanyId());
		leadColumnRepository.save(leadColumn);

		// 2. Save the lead
		Lead lead = new Lead();
		lead.setFields(dto.getLead());
		lead.setCompanyId(Long.valueOf(company.getCompanyId()));
		lead.setCreatedDate(LocalDateTime.now());
		lead.setUpdatedDate(LocalDateTime.now());
		lead.setStatus(dto.getStatus());
		lead.setSource(dto.getSource());
		lead.setAssignTo(dto.getAssignTo());
		lead.setEmployeeId(dto.getEmployeeId());
		leadRepository.save(lead);

		// 3. Return updated columns and saved lead
		return dto;
	}

	@GetMapping("/getAllLeads/{page}/{size}")
	public ResponseEntity<?> getAllLeads(@PathVariable int page ,@PathVariable int size,@RequestParam(defaultValue = "") String name) {

	    try {
	        // 1. Fetch column metadata for company
	        LeadColumn leadColumn = leadColumnRepository.findByCompanyId(company.getCompanyId());
	        List<LeadColumn.ColumnDefinition> sortedColumns = leadColumn.getColumns()
	                .stream()
	                .sorted(Comparator.comparingInt(LeadColumn.ColumnDefinition::getSequence))
	                .toList();

	        // 2. Fetch paginated leads for company
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Lead> leadPage =null;
	        if(name.isEmpty()) {
	         leadPage = leadRepository.findByCompanyIdOrderByIdDesc(company.getCompanyId(), pageable);
	        }else {
				// Build dynamic OR criteria for all columns
				List<Criteria> fieldCriteria = new ArrayList<>();
				for (LeadColumn.ColumnDefinition colDef : sortedColumns) {
					fieldCriteria.add(Criteria.where("fields." + colDef.getName()).regex(name, "i"));
				}

				Criteria criteria = new Criteria().andOperator(Criteria.where("companyId").is(company.getCompanyId()),
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



	@PutMapping("/updateLead/{id}")
	public ResponseEntity<?> updateLead(@PathVariable String id, @RequestBody LeadWithColumnsDTO dto) {
		try {
			Map<String,Object> leadInfo=new HashMap<>();
			LeadColumn leadColumn = leadColumnRepository.findByCompanyId(company.getCompanyId());
			Lead lead = leadRepository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found"));

			if (leadColumn == null) {
				leadColumn = new LeadColumn();
				leadColumn.setColumns(dto.getColumns());

			} else {
				leadColumn.setColumns(dto.getColumns());
			}

			leadColumn.setCompanyId(company.getCompanyId());

			lead.setFields(dto.getLead());
			
			leadRepository.save(lead);
			leadColumnRepository.save(leadColumn);
			
			leadInfo.put("lead",lead);
			leadInfo.put("leadColumn", leadColumn);
			return ResponseEntity.ok(leadInfo);
		} catch (Exception e) {

			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}
	}
	
	@GetMapping("/getLeadById/{leadId}")
	public ResponseEntity<?> getLeadById(@PathVariable String leadId) {
		try {
			
			Map<String,Object> leadInfo=new HashMap<>();
			
			LeadColumn leadColumn = leadColumnRepository.findByCompanyId(company.getCompanyId());
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
	
	
	
	@DeleteMapping("/deleteLead/{id}")
	public String deleteLead(@PathVariable String id) {
		leadRepository.deleteById(id);
		return "Lead deleted succefully ";
	}


	@PostMapping("/createColumn")
	public LeadColumn createColumn(@RequestBody LeadColumn leadColumn) {
		leadColumn.setCompanyId(company.getCompanyId());
		return leadColumnRepository.save(leadColumn);
	}
	
	// Lead Colums APi as follows

	@GetMapping("/getAllColumns")
	public ResponseEntity<?> getAllColumns() {
		try {
			return ResponseEntity.ok(leadColumnRepository.findByCompanyId(company.getCompanyId()));
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}
	}

	@PutMapping("/leadColumnRename")
	public ResponseEntity<?> renameColumn(@RequestBody Map<String, String> renameRequest) {

		try {
			String oldName = renameRequest.get("oldName");
			String newName = renameRequest.get("newName");

			LeadColumn column = leadColumnRepository.findByCompanyId(company.getCompanyId());

			column.getColumns().forEach(c -> {
				if (c.getName().equals(oldName)) {
					c.setName(newName);
				}
			});

			leadColumnRepository.save(column);
			
			Query query = new Query(Criteria.where("companyId").is(company.getCompanyId()));
			Update update = new Update().rename("fields." + oldName, "fields." + newName);
			mongoTemplate.updateMulti(query, update, Lead.class);
			
			return ResponseEntity.ok(column);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}

	}

	@PutMapping("/leadColumnSequence")
	public LeadColumn updateColumnSequence(
			@RequestBody List<LeadColumn.ColumnDefinition> updatedColumns) {

		LeadColumn column = leadColumnRepository.findByCompanyId(company.getCompanyId());

		// Update only the columns provided in the request
		column.getColumns().forEach(existingColumn -> {
			updatedColumns.forEach(updated -> {
				if (existingColumn.getName().equals(updated.getName())) {
					existingColumn.setSequence(updated.getSequence());
				}
			});
		});

		// Sort the columns by the new sequence (optional)
		column.getColumns().sort((c1, c2) -> Integer.compare(c1.getSequence(), c2.getSequence()));

		return leadColumnRepository.save(column);
	}
	
	
	@DeleteMapping("/deletColumn/{columnName}")
	public ResponseEntity<String> deleteColumn(
	        @PathVariable String columnName) {

	    // 1. Find and update column sequence
	    LeadColumn leadColumn = leadColumnRepository.findByCompanyId(company.getCompanyId());
	    if (leadColumn == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("No column metadata found for company " + company.getCompanyId());
	    }
	    boolean removed = leadColumn.getColumns().removeIf(col -> col.getName().equalsIgnoreCase(columnName));
	    if (!removed) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Column '" + columnName + "' not found");
	    }
	    leadColumnRepository.save(leadColumn);

	    // 2. Use MongoTemplate to unset the field from all leads in one go
	    Query query = new Query(Criteria.where("companyId").is(company.getCompanyId()));
	    Update update = new Update().unset("fields." + columnName);
	    mongoTemplate.updateMulti(query, update, Lead.class);

	    return ResponseEntity.ok("Column '" + columnName + "' deleted successfully");
	}

	
	// lead status APIs
	
	@PostMapping("/addLeadStatus")
	public ResponseEntity<?> addLeadStatus(@RequestBody LeadStatus leadStatus){
		
		try {
			
			leadStatus.setCompanyId(company.getCompanyId());
			leadStatusRepository.save(leadStatus);
			
			return ResponseEntity.ok(leadStatus);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
			
		}
	}
	
	@GetMapping("/getLeadStaus")
	public ResponseEntity<?> getLeadStatus() {

		try {

			List<LeadStatus> leadStatus = leadStatusRepository.findByCompanyId(company.getCompanyId());

			return ResponseEntity.ok(leadStatus);

		} catch (Exception e) {
            e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteLeadStatus/{leadStatusId}")
	public ResponseEntity<?> fLeadStatus(@PathVariable String leadStatusId) {

		try {

			leadStatusRepository.deleteById(leadStatusId);

			return ResponseEntity.ok("Lead Status Deleted");

		} catch (Exception e) {
            e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateLeadStatus/{leadId}/{leadStatus}")
	public ResponseEntity<?> updateLeadStatus(@PathVariable String leadId, @PathVariable String leadStatus) {

		try {

			Lead lead = leadRepository.findByIdAndCompanyId(leadId, company.getCompanyId());
			lead.setStatus(leadStatus);
			leadRepository.save(lead);
			return ResponseEntity.ok("Lead Status Updated");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  " + e.getMessage());
		}
	}
	


}
