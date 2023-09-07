package com.backend.digitalhouse.integradorClinica.dto.entrada.paciente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true) // indica que ignore todas las propiedades desconocidas presentes en el JSON y solo mapee las propiedades que coinciden con los campos de la clase.

public class DomicilioEntradaDto {

    @NotNull(message = "La calle no puede ser nula")
    @NotBlank (message = "Debe ingresarse el nombre de la calle")
    private String calle;

    @NotNull(message = "El numero no puede ser nulo")
    @Digits(integer = 8, fraction = 0, message = "El numero debe tener como maximo 8 dígitos")
    private int numero;

    @NotNull(message = "La localidad no puede ser nula")
    @NotBlank (message = "Debe ingresarse una localidad")
    private String localidad;

    @NotNull(message = "La provincia no puede ser nula")
    @NotBlank (message = "Debe ingresarse una provincia")
    private String provincia;

    public DomicilioEntradaDto() {
    }

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
