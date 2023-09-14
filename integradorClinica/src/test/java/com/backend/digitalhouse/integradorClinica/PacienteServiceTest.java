package com.backend.digitalhouse.integradorClinica;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.impl.PacienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Test
    @Order(1)
    void seDebePoderRegistrarUnPaciente() {
        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("La Paz", 1265, "Santa Marta", "Magdalena");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Saronn", "Cordeito", 123654789, LocalDate.parse("2023-11-27"), domicilio);
        Assertions.assertEquals("Saronn", pacienteService.registrarPaciente(pacienteEntradaDto).getNombre());
    }

    @Test
    @Order(2)
    void seDebeListarTodosLosPacientes() {
        List<PacienteSalidaDto> listaPacientes = pacienteService.listarPacientes();

        Assertions.assertNotNull(listaPacientes);
    }

    @Test
    @Order(4)
    void seDebePoderModificarUnPaciente() {

        DomicilioModificacionEntradaDto domicilio = new DomicilioModificacionEntradaDto(1L, "La Paz", 1265, "Santa Marta", "Magdalena");

        PacienteModificacionEntradaDto pacienteModificado = new PacienteModificacionEntradaDto(1L, "Mateo", "Roman", 45698713, LocalDate.parse("2023-10-17"), domicilio);

        Assertions.assertEquals("Mateo", pacienteService.modificarPaciente(pacienteModificado).getNombre());
    }

    @Test
    @Order(3)
    void seDebePoderBuscarPorIdUnPaciente() {
        Assertions.assertNotNull(pacienteService.buscarPacientePorId(1L));
    }

    @Test
    @Order(5)
    void seDebePoderEliminarUnPaciente() {
        try {
            pacienteService.eliminarPaciente(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }
}
