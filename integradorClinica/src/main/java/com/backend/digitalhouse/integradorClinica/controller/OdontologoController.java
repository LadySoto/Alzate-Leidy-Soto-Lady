package com.backend.digitalhouse.integradorClinica.controller;

import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //Endpoint para registrar odontologo - Tipo POST
    @PostMapping("registrar")
    public Odontologo registrarOdontologo(@RequestBody OdontologoEntradaDto odontologo){
        return odontologoService.registrarOdontologo(odontologo);
    }

    //Endpoint para modificar odontologo - Tipo PUT
    @PutMapping("modificar")
    public Odontologo modificarOdontologo(@RequestBody Odontologo odontologoModificado){
        return odontologoService.modificarOdontologo(odontologoModificado);
    }

    //Endpoint para buscar odontologo por id - Tipo GET
    @GetMapping("buscar")
    public Odontologo buscarOdontologoPorId(@RequestParam int id){
        return odontologoService.buscarOdontologoPorId(id);
    }

    //Endpoint para eliminar odontologo por id - Tipo DELET
    @DeleteMapping("eliminar/{id}")
    public void eliminarOdontologoPorId(@PathVariable int id){
        odontologoService.eliminarOdontologo(id);
    }

    //Endpoint para listar odontologos - Tipo GET
    @GetMapping("listar")
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }
}
