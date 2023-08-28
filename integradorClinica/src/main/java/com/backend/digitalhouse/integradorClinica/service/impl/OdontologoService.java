package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.repository.IDao;
import com.backend.digitalhouse.integradorClinica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final IDao<Odontologo> odontologoIDao;
    private final ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {

        Odontologo odontologoNuevo = odontologoIDao.registrar(mapToEntity(odontologo));

        return mapToDtoSalida(odontologoNuevo);
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoIDao.listar();
    }

    @Override
    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    @Override
    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);

    }
    @Override
    public Odontologo modificarOdontologo(Odontologo odontologoModificado) {
        return odontologoIDao.modificar(odontologoModificado);
    }

    public Odontologo mapToEntity(OdontologoEntradaDto odontologoEntradaDto){
        return modelMapper.map(odontologoEntradaDto, Odontologo.class);
    }

    public OdontologoSalidaDto mapToDtoSalida(Odontologo odontologo){
        return modelMapper.map(odontologo, OdontologoSalidaDto.class);
    }
}