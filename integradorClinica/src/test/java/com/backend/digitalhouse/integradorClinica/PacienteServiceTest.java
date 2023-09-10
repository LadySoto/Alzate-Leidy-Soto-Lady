package com.backend.digitalhouse.integradorClinica;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.entity.Domicilio;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.impl.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Test
    void seDebePoderRegistrarUnPaciente(){
        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("La Paz",1265,"Santa Marta", "Magdalena");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Saronn","Cordeito",123654789, LocalDate.parse("2023-11-27"), domicilio);
        Assertions.assertEquals("Saronn",pacienteService.registrarPaciente(pacienteEntradaDto).getNombre());
    }

    @Test
    void seDebeListarTodosLosPacientes(){
        ArrayList listaPacientes = new ArrayList<>(pacienteService.listarPacientes());

        Assertions.assertNotNull(listaPacientes);
    }

    @Test
    void seDebePoderModificarUnPaciente(){

        DomicilioModificacionEntradaDto domicilio = new DomicilioModificacionEntradaDto(3L,"La Paz",1265,"Santa Marta", "Magdalena");

        PacienteModificacionEntradaDto pacienteModificado = new PacienteModificacionEntradaDto(3L,"Mateo","Roman", 45698713,LocalDate.parse("2023-10-17"),domicilio);

        Assertions.assertEquals("Mateo",pacienteService.modificarPaciente(pacienteModificado).getNombre());
    }

    @Test
    void seDebePoderBuscarPorIdUnPaciente(){
        Assertions.assertNotNull(pacienteService.buscarPacientePorId(2L));
    }

    @Test
    void seDebePoderEliminarUnPaciente() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(2L);
        Assertions.assertNull(pacienteService.buscarPacientePorId(2L));
    }
}
