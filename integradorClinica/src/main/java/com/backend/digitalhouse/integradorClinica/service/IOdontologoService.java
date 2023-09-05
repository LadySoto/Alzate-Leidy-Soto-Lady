package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    OdontologoSalidaDto registrarOdontologo (OdontologoEntradaDto odontologo);
    List<OdontologoSalidaDto> listarOdontologos();
    OdontologoSalidaDto buscarOdontologoPorId (long id);
    void eliminarOdontologo (long id) throws ResourceNotFoundException;
    OdontologoSalidaDto modificarOdontologo (OdontologoModificacionEntradaDto odontologoModificado);

}
