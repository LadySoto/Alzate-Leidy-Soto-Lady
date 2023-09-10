package com.backend.digitalhouse.integradorClinica;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    void seRegistraUnOdontologo(){

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("AH-1236987","Marcos","Franca");

        OdontologoSalidaDto rtaObtenida = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("AH-1236987",rtaObtenida.getMatricula());
    }

    @Test
    void debeListartodosLosOdontologos(){
        ArrayList listaOdontologos = new ArrayList<>(odontologoService.listarOdontologos());
        assertNotNull(listaOdontologos);
    }

    @Test
    void seDebePoderModificarUnOdontologo(){
        OdontologoModificacionEntradaDto odontologoModificado = new OdontologoModificacionEntradaDto(1L,"AH-1236987","Felipe","Gomez");

        assertEquals("Felipe",odontologoService.modificarOdontologo(odontologoModificado).getNombre());
    }

    @Test
    void sePuedeBuscarUnOdontologo(){
        assertEquals("Marcos", odontologoService.buscarOdontologoPorId(3L).getNombre());
    }

    @Test
    void sePuedeEliminarUnOdontologo() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(3L);
        assertNull(odontologoService.buscarOdontologoPorId(3L));

    }

}
