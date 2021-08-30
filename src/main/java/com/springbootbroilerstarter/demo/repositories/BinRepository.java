package com.springbootbroilerstarter.demo.repositories;

import com.springbootbroilerstarter.demo.domains.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {
}
