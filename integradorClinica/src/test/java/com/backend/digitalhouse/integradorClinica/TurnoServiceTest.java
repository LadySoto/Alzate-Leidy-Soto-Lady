package com.backend.digitalhouse.integradorClinica;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integradorClinica.exceptions.BadRequestException;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.impl.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
public class TurnoServiceTest {

   @Autowired
    TurnoService turnoService;

    @Test
    void seDebePoderRegistrarUnTurno() throws BadRequestException {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(4L,5L, LocalDateTime.parse("2023-11-21T12:15"));

        Assertions.assertEquals(2L,turnoService.registrarTurno(turnoEntradaDto).getId());
    }

    @Test
    void seDebePoderListarLosTurnos(){
        ArrayList listaTurnos = new ArrayList(turnoService.listarTurnos());

        Assertions.assertNotNull(listaTurnos);
    }

    @Test
    void seDebePoderModificarUnTurno() throws ResourceNotFoundException {
        TurnoModificacionEntradaDto turnoModificado = new TurnoModificacionEntradaDto(1L,6L,1L,LocalDateTime.parse("2023-12-31T12:15"));

        Assertions.assertEquals(6L,turnoService.modificarTurno(turnoModificado).getOdontologoTurnoSalidaDto().getId());

    }

    @Test
    void seDebePoderBuscarUnTurnoPorId(){
        Assertions.assertNotNull(turnoService.buscarTurnoPorId(1L));

    }

    @Test
    void seDebePoderEliminarUnTurno() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertNull(turnoService.buscarTurnoPorId(1L));
    }

}
