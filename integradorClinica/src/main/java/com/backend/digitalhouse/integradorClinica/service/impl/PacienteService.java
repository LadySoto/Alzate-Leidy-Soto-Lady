package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.repository.IDao;
import com.backend.digitalhouse.integradorClinica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        return mapToDtoSalida(pacienteRegistrado);
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<Paciente> pacientes = pacienteIDao.listar();
        //List<PacienteSalidaDto> pacienteSalidaDtos = new ArrayList<>();
        return pacientes.stream().map(this :: mapToDtoSalida).toList();
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
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) {

        PacienteSalidaDto pacienteSalidaDto = null;
        Paciente pacienteAModificar = pacienteIDao.buscarPorId(pacienteModificado.getId()); //DUDA AQUI: AQUI EL IDAO ESTA TRABAJANDO CON ENTIDAD O CON PACIENTEMODIFICACIONENTRADADTO?

        if(pacienteAModificar != null){
            pacienteAModificar = dtoModificadoEntidad(pacienteModificado);
            pacienteSalidaDto = mapToDtoSalida(pacienteIDao.modificar(pacienteAModificar));
        }

        return pacienteSalidaDto;
    }

    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio));
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));
    }

    public Paciente mapToEntity(PacienteEntradaDto pacienteEntradaDto){
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

    public PacienteSalidaDto mapToDtoSalida(Paciente paciente){
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    public Paciente dtoModificadoEntidad(PacienteModificacionEntradaDto pacienteEntradaDto){
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

}

