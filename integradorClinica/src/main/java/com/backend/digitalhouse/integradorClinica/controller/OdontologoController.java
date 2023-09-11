package com.backend.digitalhouse.integradorClinica.controller;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@Valid @RequestBody OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

    //Endpoint para modificar odontologo - Tipo PUT
    @PutMapping("modificar")
    public ResponseEntity<OdontologoSalidaDto> modificarOdontologo(@Valid @RequestBody OdontologoModificacionEntradaDto odontologoModificado){
        return new ResponseEntity<>(odontologoService.modificarOdontologo(odontologoModificado),HttpStatus.OK ) ;
    }

    //Endpoint para buscar odontologo por id - Tipo GET
    @GetMapping("buscar/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id),HttpStatus.OK );
    }

    //Endpoint para eliminar odontologo por id - Tipo DELET
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente",HttpStatus.NO_CONTENT);
    }

    //Endpoint para listar odontologos - Tipo GET
    @GetMapping("listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos(){
        return new ResponseEntity<>(odontologoService.listarOdontologos(),HttpStatus.OK );
    }
}
