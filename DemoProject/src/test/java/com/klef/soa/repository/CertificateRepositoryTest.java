
package com.klef.soa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.entity.Certificate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CertificateRepositoryTest {

    @Autowired
    private CertificateRepository repository;

    // Test case 1: Find All
    @Test
    void testFindAllCertificates() {

        Certificate c = new Certificate();
        c.setName("Rahul");
        c.setType("Java");
        c.setStatus("Completed");
        c.setLocation("Hyderabad");

        repository.save(c);

        List<Certificate> certificates = repository.findAll();

        assertNotNull(certificates);
        assertTrue(certificates.stream()
                .anyMatch(cert -> cert.getName().equals("Rahul")));

        System.out.println("Total Certificates: " + certificates.size());
    }

    // Test case 2: Find by ID
    @Test
    void testFindCertificateById() {

        Certificate c = new Certificate();
        c.setName("Keerthana");
        c.setType("AWS");
        c.setStatus("Completed");
        c.setLocation("India");

        Certificate saved = repository.save(c);

        Optional<Certificate> certificate =
                repository.findById(saved.getCertificateId());

        assertTrue(certificate.isPresent());
        assertEquals(saved.getCertificateId(),
                certificate.get().getCertificateId());

        System.out.println("Certificate ID: "
                + certificate.get().getCertificateId());

        Optional<Certificate> invalid =
                repository.findById(-1L);

        assertFalse(invalid.isPresent());
    }

    // Test case 3: Find by Derived Query
    @Test
    void testFindCertificateByName() {

        Certificate c = new Certificate();
        c.setName("Priya");
        c.setType("Python");
        c.setStatus("Completed");
        c.setLocation("Chennai");

        repository.save(c);

        List<Certificate> certificates =
                repository.findByName("Priya");

        assertFalse(certificates.isEmpty());
        assertEquals("Priya", certificates.get(0).getName());

        System.out.println("Certificate Name: "
                + certificates.get(0).getName());
    }

    // Test case 4: Count
    @Test
    void testTotalCertificateCount() {

        long initialCount = repository.count();

        Certificate c1 = new Certificate();
        c1.setName("Arjun");
        c1.setType("Cloud");
        c1.setStatus("Completed");
        c1.setLocation("Bangalore");

        Certificate c2 = new Certificate();
        c2.setName("Divya");
        c2.setType("Spring Boot");
        c2.setStatus("Completed");
        c2.setLocation("Vijayawada");

        repository.save(c1);
        repository.save(c2);

        long count = repository.count();

        assertEquals(initialCount + 2, count);

        System.out.println("Total Certificates: " + count);
    }

    // Test case 5: Entity Details / Property-Based Result
    @Test
    void testCertificateDetails() {

        Certificate c = new Certificate();
        c.setName("Anu");
        c.setType("Database");
        c.setStatus("Completed");
        c.setLocation("Delhi");

        Certificate saved = repository.saveAndFlush(c);

        assertNotNull(saved.getCertificateId());
        assertNotNull(saved.getName());
        assertNotNull(saved.getType());
        assertNotNull(saved.getStatus());
        assertNotNull(saved.getLocation());

        System.out.println("Name: " + saved.getName());
        System.out.println("Type: " + saved.getType());
        System.out.println("Status: " + saved.getStatus());
        System.out.println("Location: " + saved.getLocation());
    }
}
