package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente registrar (Paciente paciente);
    List<Paciente> listar();
    Paciente buscarPorId (int id);

    void eliminar (int id);
    Paciente modificar (Paciente paciente);
}
