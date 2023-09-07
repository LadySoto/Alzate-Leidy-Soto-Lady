package com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioModificacionEntradaDto {
    @NotNull(message = "Se necesita ingresar un id")
    private Long id;
    @NotNull(message = "Se necesita ingresar una calle")
    @NotBlank(message = "Debe especificar la calle")
    private String calle;
    @NotNull(message = "Se necesita ingresar un número")
    private int numero;

    @NotNull(message = "Se necesita ingresar una localidad")
    @NotBlank(message = "Debe especificar la localidad")
    private String localidad;

    @NotNull(message = "Se necesita ingresar una provincia")
    @NotBlank(message = "Debe especificar la provincia")
    private String provincia;

    public DomicilioModificacionEntradaDto() {
    }

    public DomicilioModificacionEntradaDto(Long id, String calle, int numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



