package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Certificate;
import com.klef.soa.repository.CertificateRepository;

@Service
public class CertificateImpl implements CertificateService
{
	@Autowired
	private CertificateRepository repo;

	@Override
	public Certificate addCertificate(Certificate certificate)
	{
		return repo.save(certificate);
	}

	@Override
	public List<Certificate> displayAllCertificates()
	{
		return repo.findAll();
	}

	@Override
	public Certificate updateCertificate(Certificate c)
	{
		Optional<Certificate> optional = repo.findById(c.getCertificateId());

		if (optional.isPresent())
		{
			Certificate certificate = optional.get();
			certificate.setName(c.getName());
			certificate.setType(c.getType());
			certificate.setStatus(c.getStatus());
			certificate.setLocation(c.getLocation());

			return repo.save(certificate);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Certificate displayCertificateById(Long certificateId)
	{
		return repo.findById(certificateId).orElse(null);
	}

	@Override
	public String deleteCertificateById(Long certificateId)
	{
		boolean status = repo.existsById(certificateId);

		if (status)
		{
			repo.deleteById(certificateId);
			return "Certificate Deleted Successfully";
		}
		else
		{
			return "Certificate ID Not Found";
		}
	}
}