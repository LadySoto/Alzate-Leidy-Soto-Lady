package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.repository.OdontologoRepository;
import com.backend.digitalhouse.integradorClinica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {

        Odontologo odontoRegistrado = odontologoRepository.save(mapToEntity(odontologo));
        OdontologoSalidaDto odontologoSalidaDto = mapToDtoSalida(odontoRegistrado);
        LOGGER.info("Odontologo guardado: {}", odontologoSalidaDto);

        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        LOGGER.info("Listado de odontologos: {}", listaOdontologos);
        return listaOdontologos.stream().map(this::mapToDtoSalida).toList();
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoABuscar = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoABuscar != null){
            odontologoSalidaDto = mapToDtoSalida(odontologoABuscar);
            LOGGER.info("Odontologo encontrado: {}", odontologoSalidaDto);
        }else {
            LOGGER.error("Odontologo no encontrado");
        }

        return odontologoSalidaDto;
    }


    @Override
    public void eliminarOdontologo(Long id) {
        Odontologo odontologoABuscar = odontologoRepository.findById(id).orElse(null);

        if (odontologoABuscar != null){
            odontologoRepository.deleteById(id);
            LOGGER.info("Se elimino el odontologo con ID: {}", id);
        }else {
            LOGGER.error("Odontologo no encontrado");
        }
    }

    @Override
    public OdontologoSalidaDto modificarOdontologo(OdontologoModificacionEntradaDto odontologoModificado) {
        OdontologoSalidaDto odontologoSalidaDto = null;

        if (buscarOdontologoPorId(odontologoModificado.getId()) != null){
            odontologoSalidaDto = mapToDtoSalida(odontologoRepository.save(mapDtoModificadoToEntity(odontologoModificado)));
            LOGGER.info("El odontologo: {}, fue modificado exitosamente", odontologoModificado);
        }else{
            LOGGER.error("el odontologo: {}, no pudo ser modificado porque no se encontró", odontologoModificado);
        }
        return odontologoSalidaDto;
    }

    public Odontologo mapToEntity(OdontologoEntradaDto odontologoEntradaDto){
        return modelMapper.map(odontologoEntradaDto, Odontologo.class);
    }

    public Odontologo mapDtoModificadoToEntity(OdontologoModificacionEntradaDto odontologoModificado){
        return modelMapper.map(odontologoModificado, Odontologo.class);
    }

    public OdontologoSalidaDto mapToDtoSalida(Odontologo odontologo){
        return modelMapper.map(odontologo, OdontologoSalidaDto.class);
    }
}