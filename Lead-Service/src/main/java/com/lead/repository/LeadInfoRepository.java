package com.lead.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lead.entity.Lead;

public interface LeadInfoRepository extends MongoRepository<Lead, String>{

}
