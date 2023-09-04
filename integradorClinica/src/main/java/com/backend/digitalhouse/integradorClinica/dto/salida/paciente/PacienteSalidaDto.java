package com.backend.digitalhouse.integradorClinica.dto.salida.paciente;

import com.backend.digitalhouse.integradorClinica.entity.Domicilio;

import java.time.LocalDate;

public class PacienteSalidaDto {

    private long id;
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaDeIngreso;
    private DomicilioSalidaDto domicilio;

    public PacienteSalidaDto() {
    }

    public PacienteSalidaDto(long id, String nombre, String apellido, int dni, LocalDate fechaDeIngreso, DomicilioSalidaDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public DomicilioSalidaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioSalidaDto domicilio) {
        this.domicilio = domicilio;
    }
    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - DNI: " + dni + " - Fechas de ingreso: " + fechaDeIngreso + " - Domicilio: " + domicilio;
    }

}
