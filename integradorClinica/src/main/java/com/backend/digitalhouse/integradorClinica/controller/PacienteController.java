package com.backend.digitalhouse.integradorClinica.controller;

import com.backend.digitalhouse.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Paciente registrarPaciente(@RequestBody PacienteEntradaDto paciente){
        return pacienteService.registrarPaciente(paciente);
    }

    //Endoint para modificar un paciente - Put
    @PutMapping("modificar")
    public Paciente modificarPaciente(@RequestBody PacienteEntradaDto paciente){
        return pacienteService.modificarPaciente(paciente);
    }

    //Endpoint para buscar un pacientes por id - Tipo GET
    @GetMapping("buscar")
    public Paciente buscarPacientePorId(@RequestParam int id){
        return pacienteService.buscarPacientePorId(id);
    }

    //Endpoint para eliminar un pacientes por id - Tipo DELET
    @DeleteMapping("eliminar/{id}")
    public void eliminarPaciente(@PathVariable int id){
        pacienteService.eliminarPaciente(id);
    }

    //Endponit para listar los pacientes - Tipo GET
    @GetMapping("listar")
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }

}
