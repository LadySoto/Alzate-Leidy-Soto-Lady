package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo registrar (Odontologo odontologo);
    List<Odontologo> listar();
    Odontologo buscarPorId (int id);
    void eliminar (int id);
    Odontologo modificar (Odontologo odontologo);

}
