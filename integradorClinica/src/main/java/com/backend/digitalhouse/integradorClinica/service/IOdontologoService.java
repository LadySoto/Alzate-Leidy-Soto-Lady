package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    OdontologoSalidaDto registrarOdontologo (OdontologoEntradaDto odontologo);
    List<Odontologo> listarOdontologos();
    Odontologo buscarOdontologoPorId (int id);
    void eliminarOdontologo (int id);
    Odontologo modificarOdontologo (Odontologo odontologoModificado);

}
