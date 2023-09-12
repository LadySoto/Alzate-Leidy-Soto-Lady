package com.backend.digitalhouse.integradorClinica;

import com.backend.digitalhouse.integradorClinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integradorClinica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integradorClinica.service.impl.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void debePermitirRegistraUnOdontologo(){

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("AH-1236987","Marcos","Franca");

        OdontologoSalidaDto rtaObtenida = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("AH-1236987",rtaObtenida.getMatricula());
    }

    @Test
    @Order(2)
    void debeListartodosLosOdontologos(){
        List<OdontologoSalidaDto> listaOdontologos = odontologoService.listarOdontologos();
        assertNotNull(listaOdontologos);
    }

    @Test
    @Order(4)
    void seDebePoderModificarUnOdontologo(){
        OdontologoModificacionEntradaDto odontologoModificado = new OdontologoModificacionEntradaDto(1L,"AH-1236987","Felipe","Gomez");

        assertEquals("Felipe",odontologoService.modificarOdontologo(odontologoModificado).getNombre());
    }

    @Test
    @Order(3)
    void sePuedeBuscarUnOdontologoPorId(){
        assertEquals("Marcos", odontologoService.buscarOdontologoPorId(1L).getNombre());
    }

    @Test
    @Order(5)
    void sePuedeEliminarUnOdontologoPorId(){
        try {
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

}
