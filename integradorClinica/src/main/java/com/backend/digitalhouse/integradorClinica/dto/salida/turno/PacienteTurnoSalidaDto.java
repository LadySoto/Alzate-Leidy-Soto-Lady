package com.backend.digitalhouse.integradorClinica.dto.salida.turno;

public class PacienteTurnoSalidaDto {
    private long id;
    private String nombre;
    private String apellido;

    public PacienteTurnoSalidaDto() {
    }

    public PacienteTurnoSalidaDto(long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
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
}
