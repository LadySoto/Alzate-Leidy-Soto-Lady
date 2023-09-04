package com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion;

import com.backend.digitalhouse.integradorClinica.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteModificacionEntradaDto {
    @NotNull
    private Long id;
    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private int dni;
    @NotNull
    private LocalDate fechaDeIngreso;

    private DomicilioModificacionEntradaDto domicilio;

    public PacienteModificacionEntradaDto() {
    }

    public PacienteModificacionEntradaDto(Long id, String nombre, String apellido, int dni, LocalDate fechaDeIngreso, DomicilioModificacionEntradaDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public DomicilioModificacionEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioModificacionEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}

