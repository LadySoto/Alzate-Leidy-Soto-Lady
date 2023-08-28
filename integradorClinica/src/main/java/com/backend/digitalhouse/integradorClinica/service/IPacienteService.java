package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    PacienteSalidaDto registrarPaciente (PacienteEntradaDto paciente);
    List<Paciente> listarPacientes();
    Paciente buscarPacientePorId (int id);

    void eliminarPaciente (int id);
    Paciente modificarPaciente (Paciente pacienteModificado);
}
