package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.repository.PacienteRepository;
import com.backend.digitalhouse.integradorClinica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        Paciente pacienteRegistrado = pacienteRepository.save(mapToEntity(paciente));
        PacienteSalidaDto pacienteSalidaDto = mapToDtoSalida(pacienteRegistrado);
        LOGGER.info("Paciente guardado: {}", pacienteSalidaDto);

        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        LOGGER.info("Listado de pacientes: {}", listaPacientes);
        return listaPacientes.stream()
                .map(this::mapToDtoSalida)
                .toList();
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteABuscar = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;

        if (pacienteABuscar != null){
            pacienteSalidaDto = mapToDtoSalida(pacienteABuscar);
            LOGGER.info("Paciente encontrado: {}", pacienteSalidaDto);
        }else {
            LOGGER.error("Paciente no encontrado");
        }

        return pacienteSalidaDto;
    }

    @Override
    public void eliminarPaciente(Long id) {
        Paciente pacienteABuscar = pacienteRepository.findById(id).orElse(null);

        if (pacienteABuscar != null){
            pacienteRepository.deleteById(id);
            LOGGER.info("Se elimino el paciente con ID: {}", id);
        }else {
            LOGGER.error("No se pudo eliminar el paciente con id: {}, porque no fue encontrado", id);
        }
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) {
        PacienteSalidaDto pacienteSalidaDto = null;

        if (buscarPacientePorId(pacienteModificado.getId()) != null){
            pacienteSalidaDto = mapToDtoSalida(pacienteRepository.save(mapDtoModificadoToEntity(pacienteModificado)));
            LOGGER.info("El paciente: {}, fue modificado exitosamente", pacienteModificado);
        }else{
            LOGGER.error("el paciente: {}, no pudo ser modificado porque no se encontró", pacienteModificado);
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

    public Paciente mapDtoModificadoToEntity(PacienteModificacionEntradaDto pacienteEntradaDto){
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

}

