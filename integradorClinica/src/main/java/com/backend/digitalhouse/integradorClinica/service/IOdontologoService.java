package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo registrarOdontologo (Odontologo odontologo);
    List<Odontologo> listarOdontologos();
    Odontologo buscarOdontologoPorId (int id);
    void eliminarOdontologo (int id);
    Odontologo modificarOdontologo (Odontologo odontologoModificado);

}
