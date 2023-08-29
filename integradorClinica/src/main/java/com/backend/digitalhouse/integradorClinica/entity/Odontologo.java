package com.backend.digitalhouse.integradorClinica.entity;

import jdk.dynalink.linker.LinkerServices;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ODONTOLOGO_ID")
   private long id;
   private int matricula;
   private String nombre;
   private String apellido;

   @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
   private List<Turno> turnos = new ArrayList<>();

    public Odontologo() {
    }

    public Odontologo(int matricula, String nombre, String apellido, List<Turno> turnos) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.turnos = turnos;
    }

    public long getId() {
        return id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - Matricula: " + matricula;
    }
}
