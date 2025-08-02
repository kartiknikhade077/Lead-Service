package com.lead.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lead.entity.LeadColumn;

public interface LeadColumnRepository extends MongoRepository<LeadColumn, String> {
 
	LeadColumn findByCompanyId(String companyId);
}
