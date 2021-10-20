package com.springbootbroilerstarter.demo.repositories;

import com.springbootbroilerstarter.demo.domains.Invoice;
import com.springbootbroilerstarter.demo.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByInvoiceId(Long invoiceId);
}
