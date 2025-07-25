package com.lead.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lead.entity.LeadStatus;

@Repository
public interface LeadStatusRepository extends MongoRepository<LeadStatus, String>{
	
	List<LeadStatus> findByCompanyId(int companyId);

}
