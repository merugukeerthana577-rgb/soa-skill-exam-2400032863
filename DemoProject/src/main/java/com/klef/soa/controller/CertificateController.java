package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.entity.Certificate;
import com.klef.soa.service.CertificateService;

@RestController
@RequestMapping("/certificate")
public class CertificateController
{
	@Autowired
	private CertificateService service;

	@GetMapping("/")
	public String test()
	{
		return "Certificate Project";
	}

	// Add Certificate
	@PostMapping("/add")
	public ResponseEntity<Certificate> addCertificate(@RequestBody Certificate c)
	{
		Certificate certificate = service.addCertificate(c);
		return ResponseEntity.status(201).body(certificate);
	}

	// Display All Certificates
	@GetMapping("/displayall")
	public ResponseEntity<List<Certificate>> displayAllCertificates()
	{
		List<Certificate> certificates = service.displayAllCertificates();
		return ResponseEntity.status(200).body(certificates);
	}

	// Display Certificate By ID
	@GetMapping("/display")
	public ResponseEntity<?> displayCertificateById(@RequestParam Long certificateId)
	{
		Certificate certificate = service.displayCertificateById(certificateId);

		if (certificate != null)
		{
			return ResponseEntity.status(200).body(certificate);
		}
		else
		{
			return ResponseEntity.status(404).body("Certificate ID Not Found");
		}
	}

	// Update Certificate
	@PutMapping("/update")
	public ResponseEntity<?> updateCertificate(@RequestBody Certificate certificate)
	{
		Certificate c = service.updateCertificate(certificate);

		if (c != null)
		{
			return ResponseEntity.ok(c);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Certificate ID Not Found");
		}
	}

	// Delete Certificate By ID
	@DeleteMapping("/delete/{certificateId}")
	public ResponseEntity<String> deleteCertificateById(@PathVariable Long certificateId)
	{
		String message = service.deleteCertificateById(certificateId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}