package com.backend.digitalhouse.integradorClinica.repository;

import com.backend.digitalhouse.integradorClinica.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
