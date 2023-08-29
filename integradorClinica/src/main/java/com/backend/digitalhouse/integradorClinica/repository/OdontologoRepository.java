package com.backend.digitalhouse.integradorClinica.repository;

import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
