package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.repository.IDao;
import com.backend.digitalhouse.integradorClinica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoIDao.registrar(odontologo);
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
}