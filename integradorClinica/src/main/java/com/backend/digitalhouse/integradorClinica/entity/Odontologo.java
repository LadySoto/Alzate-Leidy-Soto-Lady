package com.backend.digitalhouse.integradorClinica.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ODONTOLOGO_ID")
    private Long id;


    private String matricula;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Turno> turnos = new ArrayList<>();

    public Odontologo() {
    }

    public Odontologo(String matricula, String nombre, String apellido, List<Turno> turnos) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.turnos = turnos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
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

}
