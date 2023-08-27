package com.backend.digitalhouse.integradorClinica.dto.entrada.domicilio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true) // indica que ignore todas las propiedades desconocidas presentes en el JSON y solo mapee las propiedades que coinciden con los campos de la clase

public class DomicilioEntradaDto {

    @NotNull(message = "La calle no puede ser nula")
    @NotBlank (message = "Debe ingresarse el nombre de la calle")
    private String calle;

    @NotNull(message = "El numero no puede ser nulo")
    @NotBlank (message = "Debe ingresarse el numero")
    //@Digits(integer = 8, fraction = 0, message = "El numero debe tener como maximo 8 dígitos")
    @Pattern(regexp = "\\d{1,8}", message = "El numero debe tener como maximo 8 dígitos")
    private int numero;

    @NotNull(message = "La localidad no puede ser nula")
    @NotBlank (message = "Debe ingresarse una localidad")
    private String localidad;

    @NotNull(message = "La provincia no puede ser nula")
    @NotBlank (message = "Debe ingresarse una provincia")
    private String provincia;


}
