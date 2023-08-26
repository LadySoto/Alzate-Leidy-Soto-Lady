package com.backend.digitalhouse.integradorClinica.controller;

import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.registrarPaciente(paciente);

    }

    //Endoint para modificar un paciente - Put
    @PutMapping("modificar")
    public Paciente modificarPaciente(@RequestBody Paciente paciente){
        return pacienteService.modificarPaciente(paciente);
    }

}
