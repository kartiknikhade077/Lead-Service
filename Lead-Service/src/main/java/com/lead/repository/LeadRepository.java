package com.lead.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.lead.entity.Lead;

public interface LeadRepository extends MongoRepository<Lead, String> {

	Page<Lead> findByCompanyIdOrderByIdDesc(long companyId,Pageable pageable);
}
