package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Certificate;

public interface CertificateService
{
	Certificate addCertificate(Certificate certificate);

	Certificate updateCertificate(Certificate certificate);

	String deleteCertificateById(Long certificateId);

	List<Certificate> displayAllCertificates();

	Certificate displayCertificateById(Long certificateId);
}