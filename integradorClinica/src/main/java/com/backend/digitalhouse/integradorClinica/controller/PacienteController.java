package com.backend.digitalhouse.integradorClinica.controller;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //Endpoint para registrar paciente - Tipo POST
    @PostMapping("registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@Valid @RequestBody PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    //Endoint para modificar un paciente - Put
    @PutMapping("modificar")
    public ResponseEntity<PacienteSalidaDto> modificarPaciente(@Valid @RequestBody PacienteModificacionEntradaDto pacienteModificado){
        return new ResponseEntity<>(pacienteService.modificarPaciente(pacienteModificado), HttpStatus.OK) ;
    }

    //Endpoint para buscar un pacientes por id - Tipo GET
    @GetMapping("buscar")
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@RequestParam long id){
        return new ResponseEntity<> (pacienteService.buscarPacientePorId(id),HttpStatus.OK );
    }

    //Endpoint para eliminar un pacientes por id - Tipo DELET
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente",HttpStatus.NO_CONTENT);
    }

    //Endponit para listar los pacientes - Tipo GET
    @GetMapping("listar")
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK) ;
    }

}
