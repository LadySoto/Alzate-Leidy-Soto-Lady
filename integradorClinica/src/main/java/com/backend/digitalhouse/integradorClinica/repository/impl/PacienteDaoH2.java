package com.backend.digitalhouse.integradorClinica.repository.impl;

import com.backend.digitalhouse.integradorClinica.entity.Domicilio;
import com.backend.digitalhouse.integradorClinica.entity.Paciente;
import com.backend.digitalhouse.integradorClinica.repository.H2Connection;
import com.backend.digitalhouse.integradorClinica.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Component

public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);
    @Override
    public Paciente registrar(Paciente paciente) {
        Connection connection = null;
        String insert = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_DE_INGRESO, ID_DOMICILIO ) VALUES (?,?,?,?,?)";
        Paciente paciente1= null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            Domicilio domicilio = domicilioDaoH2.registrar(paciente.getDomicilio());

            PreparedStatement pst = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, paciente.getNombre());
            pst.setString(2, paciente.getApellido());
            pst.setInt(3, paciente.getDni());
            pst.setDate(4, Date.valueOf(paciente.getFechaDeIngreso()));
            pst.setInt(5,paciente.getDomicilio().getId());
            pst.execute();

            connection.commit();
            paciente1 = new Paciente (paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaDeIngreso(), domicilio);
            ResultSet key = pst.getGeneratedKeys();
            while (key.next()) {
                paciente1.setId(key.getInt(1));
            }
            if(paciente1 == null) LOGGER.error("El paciente no pudo se registrado");
            else LOGGER.info("Paciente registrado: {} ", paciente1);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return paciente1;
    }

    @Override
    public List<Paciente> listar() {
        Connection connection = null;
        Paciente paciente = null;
        List<Paciente> pacientesLista = new ArrayList<>();
        String select = "SELECT * FROM PACIENTES";

        try {
            connection = H2Connection.getConnection();
            PreparedStatement pst = connection.prepareStatement(select);
            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                paciente = crearObjetoPaciente(rst);
                pacientesLista.add(paciente);
            }
            LOGGER.info("Se generó la lista de pacientes: {}", pacientesLista);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return pacientesLista;
    }

    @Override
    public Paciente buscarPorId(int id) {
        Connection connection = null;
        Paciente paciente = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                paciente = crearObjetoPaciente(rs);
            }
            LOGGER.info("Se ha encontrado el paciente con id {}: {}", id, paciente);

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

        return paciente;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        String delete = "DELETE FROM PACIENTES WHERE ID = ?";

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, id);
            pst.execute();

            connection.commit();
            LOGGER.info("El paciente con id: {} fue eliminado de la base de datos.",id);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

    }

    @Override
    public Paciente modificar(Paciente pacienteModificado) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA_DE_INGRESO = ?, ID_DOMICILIO = ? WHERE ID = ?");
            ps.setString(1,pacienteModificado.getNombre());
            ps.setString(2, pacienteModificado.getApellido());
            ps.setInt(3, pacienteModificado.getDni());
            ps.setDate(4, Date.valueOf(pacienteModificado.getFechaDeIngreso()));
            ps.setInt(5, pacienteModificado.getDomicilio().getId());
            ps.setInt(6, pacienteModificado.getId());
            ps.execute();

            LOGGER.warn("El paciente con id " + pacienteModificado.getId() + "ha sido modificado: " + pacienteModificado);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pacienteModificado;
    }

    private Paciente crearObjetoPaciente(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String nombre = rs.getString(2);
        String apellido = rs.getString(3);
        int dni = rs.getInt(4);
        LocalDate fechaDeIngreso = rs.getDate(5).toLocalDate();
        Domicilio domicilio = new DomicilioDaoH2().buscarPorId(rs.getInt(6));

        Paciente paciente = new Paciente(id,nombre, apellido, dni, fechaDeIngreso, domicilio);
        return paciente;
    }
}
