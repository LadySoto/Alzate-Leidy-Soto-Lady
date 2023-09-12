package com.backend.digitalhouse.integradorClinica;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.exceptions.BadRequestException;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.impl.OdontologoService;
import com.backend.digitalhouse.integradorClinica.service.impl.PacienteService;
import com.backend.digitalhouse.integradorClinica.service.impl.TurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest {

   @Autowired
    TurnoService turnoService;
   @Autowired
    PacienteService pacienteService;
   @Autowired
    OdontologoService odontologoService;
   private static PacienteEntradaDto paciente;
   private static OdontologoEntradaDto odontologo;
   @BeforeAll
   static void doBefore(){
       paciente = new PacienteEntradaDto("Saronn","Cordeito",123654789, LocalDate.parse("2023-11-27"), new DomicilioEntradaDto("La Paz",1265,"Santa Marta", "Magdalena"));
       odontologo = new OdontologoEntradaDto("AH-1236987","Marcos","Franca");
   }

    @Test
    @Order(1)
    void seDebePoderRegistrarUnTurno(){
       PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(paciente);
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologo);

        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(pacienteSalidaDto.getId(), odontologoSalidaDto.getId(), LocalDateTime.parse("2023-11-21T12:15"));

        try {
            Assertions.assertEquals(1L,turnoService.registrarTurno(turnoEntradaDto).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void seDebePoderListarLosTurnos(){
        List<TurnoSalidaDto> listaTurnos = turnoService.listarTurnos();

        Assertions.assertTrue(listaTurnos.size() > 0);
    }

    @Test
    @Order(4)
    void seDebePoderModificarUnTurno(){
        TurnoModificacionEntradaDto turnoModificado = new TurnoModificacionEntradaDto(1L,1L,1L,LocalDateTime.of(2023,10,31,02,35));

        try {
            Assertions.assertEquals(LocalDateTime.of(2023,10,31,02,35),turnoService.modificarTurno(turnoModificado).getFechaYHora());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    void seDebePoderBuscarUnTurnoPorId(){
        Assertions.assertNotNull(turnoService.buscarTurnoPorId(1L));

    }

    @Test
    @Order(5)
    void seDebePoderEliminarUnTurno(){
        try {
            turnoService.eliminarTurno(1L);
        } catch (Exception e) {
           e.printStackTrace();
        }
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }

}
