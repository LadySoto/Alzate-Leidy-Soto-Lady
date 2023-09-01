package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.entity.Turno;
import com.backend.digitalhouse.integradorClinica.repository.TurnoRepository;
import com.backend.digitalhouse.integradorClinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }
    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) {
        return null;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        return null;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarTurno(Long id) {

    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificado) {
        return null;
    }

    public Turno mapToEntity(TurnoEntradaDto turnoEntradaDto){
        return modelMapper.map(turnoEntradaDto, Turno.class);
    }

    public Turno mapDtoModificadoToEntity(TurnoModificacionEntradaDto turnoModificado){
        return modelMapper.map(turnoModificado, Turno.class);
    }

    public TurnoSalidaDto mapToDtoSalida(Turno turno){
        return modelMapper.map(turno, TurnoSalidaDto.class);
    }


    private void configureMapping() {
        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteTurnoSalidaDto))
                .addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoSalidaDto));
    }
}


