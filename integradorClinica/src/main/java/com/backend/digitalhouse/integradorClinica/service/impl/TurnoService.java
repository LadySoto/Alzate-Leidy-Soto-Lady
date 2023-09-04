package com.backend.digitalhouse.integradorClinica.service.impl;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
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
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        configureMapping();
    }
    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
        TurnoSalidaDto turnoSalidaDto;
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdntologoId());
        String pacienteNoEnBd ="El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBd ="El odontologo no se encuentra en nuestra base de datos";

        if(paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.info("El paciente y el odontologo no se encuentran en la base de datos");
                throw new RuntimeException("El paciente y el odontologo no se encuentran en la base de datos");
            } else if (paciente == null) {
                LOGGER.error(pacienteNoEnBd);
                throw new RuntimeException(pacienteNoEnBd);
            } else {
                LOGGER.error(odontologoNoEnBd);
                throw new RuntimeException(odontologoNoEnBd);
            }
        }else{
           Turno turnoNuevo =  turnoRepository.save(dtoEntradaAEntidad(turnoEntradaDto));
           turnoSalidaDto = entidadADtoSalida(turnoNuevo);
           LOGGER.info ("Nuevo turno registrado con exito: {}", turnoSalidaDto);

        }
        return turnoSalidaDto;

    }
    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<Turno> listadoTurnos = turnoRepository.findAll();
        LOGGER.info("Listado de turnos: {}", listadoTurnos);
        return listadoTurnos.stream()
                .map(this::entidadADtoSalida)
                .toList();
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

    public TurnoSalidaDto entidadADtoSalida (Turno turno){
        return modelMapper.map(turno, TurnoSalidaDto.class);
    }

    public Turno dtoEntradaAEntidad (TurnoEntradaDto turnoEntradaDto){
        return modelMapper.map(turnoEntradaDto, Turno.class);
    }
}


