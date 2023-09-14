package com.backend.digitalhouse.integradorClinica.service.impl;


import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.turno.OdontologoTurnoSalidaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.turno.PacienteTurnoSalidaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Turno;
import com.backend.digitalhouse.integradorClinica.exceptions.BadRequestException;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
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
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        TurnoSalidaDto turnoSalidaDto;
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());
        String pacienteNoEnBd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBd = "El odontologo no se encuentra en nuestra base de datos";

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.info("El paciente y el odontologo no se encuentran en la base de datos");
                throw new BadRequestException("No se encuentra el paciente con id: " + turnoEntradaDto.getPacienteId() + " ni el odontologo con id: " + turnoEntradaDto.getOdontologoId());
            } else if (paciente == null) {
                LOGGER.error(pacienteNoEnBd);
                throw new BadRequestException("No se encuentra el paciente con id: " + turnoEntradaDto.getPacienteId());
            } else {
                LOGGER.error(odontologoNoEnBd);
                throw new BadRequestException("No se encuentra el odontologo con id: " + turnoEntradaDto.getOdontologoId());
            }

        } else {
            Turno turnoNuevo = turnoRepository.save(dtoEntradaAEntidad(turnoEntradaDto));
            turnoSalidaDto = entidadADto(turnoNuevo);
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoSalidaDto);

        }
        return turnoSalidaDto;

    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<Turno> listadoTurnos = turnoRepository.findAll();
        LOGGER.info("Listado de turnos: {}", listadoTurnos);
        return listadoTurnos.stream()
                .map(this::entidadADto)
                .toList();
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoABuscar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoABuscar != null) {
            turnoSalidaDto = entidadADto(turnoABuscar);
            LOGGER.info("Turno encontrado: {}", turnoSalidaDto);
        } else {
            LOGGER.error("Turno no encontrado");
        }

        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Turno turnoABuscar = turnoRepository.findById(id).orElse(null);

        if (turnoABuscar != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se elimino el turno con ID: {}", id);
        } else {
            LOGGER.error("Turno no encontrado con id: {}", id);
            throw new ResourceNotFoundException("Turno no encontrado con id: " + id);
        }

    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificado) throws ResourceNotFoundException {
        TurnoSalidaDto turnoSalidaDto = null;

        if (buscarTurnoPorId(turnoModificado.getId()) != null) {
            turnoSalidaDto = entidadADto(turnoRepository.save(mapDtoModificadoToEntity(turnoModificado)));
            LOGGER.info("El turno: {}, fue modificado exitosamente", turnoModificado);
        } else {
            LOGGER.error("el turno: {}, no pudo ser modificado porque no se encontró", turnoModificado);
            throw new ResourceNotFoundException("No fue posible modificar el turno, porque no se encuentra registrado");
        }
        return turnoSalidaDto;
    }

    public Turno mapDtoModificadoToEntity(TurnoModificacionEntradaDto turnoModificado) {
        return modelMapper.map(turnoModificado, Turno.class);
    }


    public Turno dtoEntradaAEntidad(TurnoEntradaDto turnoEntradaDto) {
        return modelMapper.map(turnoEntradaDto, Turno.class);
    }

    private PacienteTurnoSalidaDto pacienteSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(pacienteService.buscarPacientePorId(id), PacienteTurnoSalidaDto.class);
    }

    private OdontologoTurnoSalidaDto odontologoSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(odontologoService.buscarOdontologoPorId(id), OdontologoTurnoSalidaDto.class);
    }

    private TurnoSalidaDto entidadADto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteTurnoSalidaDto(pacienteSalidaDtoASalidaTurnoDto(turno.getPaciente().getId()));
        turnoSalidaDto.setOdontologoTurnoSalidaDto(odontologoSalidaDtoASalidaTurnoDto(turno.getOdontologo().getId()));
        return turnoSalidaDto;
    }
}


