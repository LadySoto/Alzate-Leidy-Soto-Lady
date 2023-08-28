package com.backend.digitalhouse.integradorClinica.dto.entrada.paciente;

import com.backend.digitalhouse.integradorClinica.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;
import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteEntradaDto {

    @Size(max=50, message = "El nombre debe tener hasta 50 caracteres")
    @NotNull(message = "El nombre del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    private String nombre;

    @Size(max=50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    private String apellido;

    @Pattern(regexp = "\\d+", message = "El campo dni solo admite caracteres numéricos")
    @NotBlank(message = "Debe especificarse el dni del paciente")
    private int dni;

    @FutureOrPresent(message= "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private LocalDate fechaDeIngreso;

    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    private DomicilioEntradaDto domicilio;

    public PacienteEntradaDto() {
    }

    public PacienteEntradaDto(String nombre, String apellido, int dni, LocalDate fechaDeIngreso, DomicilioEntradaDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
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

    public DomicilioEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}

