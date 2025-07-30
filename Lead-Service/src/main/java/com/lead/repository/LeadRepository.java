package com.lead.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.lead.entity.Lead;

public interface LeadRepository extends MongoRepository<Lead, String> {

	Page<Lead> findByCompanyIdOrderByIdDesc(long companyId,Pageable pageable);
	
	Page<Lead> findByEmployeeIdOrderByIdDesc(long employeeId,Pageable pageable);
	
	@Query("{ 'companyId': ?0, 'fields': { $regex: ?1, $options: 'i' } }")
	Page<Lead> searchByAnyField(long companyId, String keyword, Pageable pageable);
	
	Lead findByIdAndCompanyId(String id,int companyId);

}
