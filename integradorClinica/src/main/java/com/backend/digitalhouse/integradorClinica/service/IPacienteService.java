package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente registrarPaciente (Paciente paciente);
    List<Paciente> listarPacientes();
    Paciente buscarPacientePorId (int id);

    void eliminarPaciente (int id);
    Paciente modificarPaciente (Paciente pacienteModificado);
}
