package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.integradorClinica.exceptions.BadRequestException;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno (TurnoEntradaDto turno) throws BadRequestException;
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurnoPorId (Long id);
    void eliminarTurno(Long id) throws ResourceNotFoundException;
    TurnoSalidaDto modificarTurno (TurnoModificacionEntradaDto turnoModificado);
}
