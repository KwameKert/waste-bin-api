package com.springbootbroilerstarter.demo.repositories;

import com.springbootbroilerstarter.demo.domains.Invoice;
import com.springbootbroilerstarter.demo.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
