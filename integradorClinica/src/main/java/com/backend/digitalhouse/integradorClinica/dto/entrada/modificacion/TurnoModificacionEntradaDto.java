package com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion;

import java.time.LocalDateTime;

public class TurnoModificacionEntradaDto {

    private Long id;
    private Long odontologoId;

    private Long pacienteId;
    private LocalDateTime fechaYHora;

    public TurnoModificacionEntradaDto() {
    }

    public TurnoModificacionEntradaDto(Long id, Long odontologoId, Long pacienteId, LocalDateTime fechaYHora) {
        this.id = id;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
