package com.backend.digitalhouse.integradorClinica.dto.salida.turno;

import java.time.LocalDateTime;

public class TurnoSalidaDto {

    private long id;
    private PacienteTurnoSalidaDto pacienteTurnoSalidaDto;
    private OdontologoSalidaDto odontologoSalidaDto;
    private LocalDateTime fechaYHora;

    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(long id, PacienteTurnoSalidaDto pacienteTurnoSalidaDto, OdontologoSalidaDto odontologoSalidaDto, LocalDateTime fechaYHora) {
        this.id = id;
        this.pacienteTurnoSalidaDto = pacienteTurnoSalidaDto;
        this.odontologoSalidaDto = odontologoSalidaDto;
        this.fechaYHora = fechaYHora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PacienteTurnoSalidaDto getPacienteTurnoSalidaDto() {
        return pacienteTurnoSalidaDto;
    }

    public void setPacienteTurnoSalidaDto(PacienteTurnoSalidaDto pacienteTurnoSalidaDto) {
        this.pacienteTurnoSalidaDto = pacienteTurnoSalidaDto;
    }

    public OdontologoSalidaDto getOdontologoSalidaDto() {
        return odontologoSalidaDto;
    }

    public void setOdontologoSalidaDto(OdontologoSalidaDto odontologoSalidaDto) {
        this.odontologoSalidaDto = odontologoSalidaDto;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
