package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long>
{
	// from Certificate c where c.name=?1
	List<Certificate> findByName(String name);

	// from Certificate c where c.type=?1
	List<Certificate> findByType(String type);
}