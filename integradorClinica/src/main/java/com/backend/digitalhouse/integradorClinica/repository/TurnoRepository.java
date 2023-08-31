package com.backend.digitalhouse.integradorClinica.repository;

import com.backend.digitalhouse.integradorClinica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

}
