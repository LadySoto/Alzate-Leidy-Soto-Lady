package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    PacienteSalidaDto registrarPaciente (PacienteEntradaDto paciente);
    List<PacienteSalidaDto> listarPacientes();
    PacienteSalidaDto buscarPacientePorId (Long id);

    void eliminarPaciente (Long id) throws ResourceNotFoundException;
    PacienteSalidaDto modificarPaciente (PacienteModificacionEntradaDto pacienteModificado);
}
