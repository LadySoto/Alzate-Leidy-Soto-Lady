package com.backend.digitalhouse.integradorClinica.service;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    OdontologoSalidaDto registrarOdontologo (OdontologoEntradaDto odontologo);
    List<OdontologoSalidaDto> listarOdontologos();
    OdontologoSalidaDto buscarOdontologoPorId (Long id);
    void eliminarOdontologo (Long id);
    OdontologoSalidaDto modificarOdontologo (OdontologoModificacionEntradaDto odontologoModificado);

}
