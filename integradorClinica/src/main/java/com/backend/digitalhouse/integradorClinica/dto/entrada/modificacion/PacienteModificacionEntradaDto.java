package com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion;

import com.backend.digitalhouse.integradorClinica.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteModificacionEntradaDto {
    @NotNull(message = "Necesita ingresar un id")
    private Long id;
    @NotNull(message = "Necesita ingresar un nombre")
    @NotBlank(message = "Debe especificar el nombre")
    private String nombre;

    @NotNull(message = "Necesita ingresar un apellido")
    @NotBlank(message = "Debe especificar el apellido")
    private String apellido;

    @NotNull(message = "Necesita ingresar un DNI")
    private int dni;
    @NotNull(message = "Necesita ingresar una fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    //@NotBlank(message = "Debe especificar la fecha")
    private LocalDate fechaDeIngreso;

    @NotNull(message = "Necesita ingresar un domicilio")
    @Valid
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

