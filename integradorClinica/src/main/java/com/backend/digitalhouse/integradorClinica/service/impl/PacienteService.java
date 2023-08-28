package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.repository.IDao;
import com.backend.digitalhouse.integradorClinica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IPacienteService {
    private final IDao<Paciente> pacienteIDao;
    private final ModelMapper modelMapper;


    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        //Convertir Dto de entrada a entidad para poder enviarla a la capa de persistencia
        Paciente pacienteApersistir = mapToEntity(paciente);
        Paciente pacienteRegistrado = pacienteIDao.registrar(pacienteApersistir);

        return pacienteIDao.registrar(paciente);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listar();
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }

    @Override
    public Paciente modificarPaciente(Paciente pacienteModificado) {

        return pacienteIDao.modificar(pacienteModificado);
    }
    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
    }

    public Paciente mapToEntity(PacienteEntradaDto pacienteEntradaDto){
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

}

