package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.repository.IDao;

import java.util.List;

public class PacienteService {
    private final IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente registrarOdontologo(Paciente odontologo) {
        return pacienteIDao.registrar(odontologo);
    }

    public List<Paciente> listarOdontologos() {
        return pacienteIDao.listar();
    }

    public Paciente buscarOdontologo(int id){return pacienteIDao.buscarPorId(id);}
    public void eliminarOdontologo(int id){pacienteIDao.eliminar(id);}
    public Paciente modificarOdontologo(Paciente odontologo){return pacienteIDao.modificar(odontologo);}
}
