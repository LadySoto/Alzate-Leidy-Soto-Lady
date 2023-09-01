package com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion;

import java.time.LocalDateTime;

public class TurnoModificacionEntradaDto {

    private long id;
    private long odontologoId;

    private long pacienteId;
    private LocalDateTime fechaYHora;

    public TurnoModificacionEntradaDto() {
    }

    public TurnoModificacionEntradaDto(long id, long odontologoId, long pacienteId, LocalDateTime fechaYHora) {
        this.id = id;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
        this.fechaYHora = fechaYHora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
