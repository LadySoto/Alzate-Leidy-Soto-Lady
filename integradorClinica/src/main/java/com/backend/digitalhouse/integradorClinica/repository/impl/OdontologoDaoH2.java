package com.backend.digitalhouse.integradorClinica.repository.impl;

import com.backend.digitalhouse.integradorClinica.entity.Odontologo;
import com.backend.digitalhouse.integradorClinica.repository.H2Connection;
import com.backend.digitalhouse.integradorClinica.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);
    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        String insert = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
        Odontologo odontologo1 = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, odontologo.getMatricula());
            pst.setString(2, odontologo.getNombre());
            pst.setString(3, odontologo.getApellido());
            pst.execute();

            connection.commit();
            odontologo1 = new Odontologo(odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            ResultSet key = pst.getGeneratedKeys();
            while (key.next()) {
                odontologo1.setId(key.getInt(1));
            }
            LOGGER.info("Odontologo registrado: " + odontologo1);

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
        return odontologo1;
    }


    @Override
    public List<Odontologo> listar() {
        Connection connection = null;
        Odontologo odontologo = null;
        List<Odontologo> odontologosLista = new ArrayList<>();
        String select = "SELECT * FROM ODONTOLOGOS";

        try {
            connection = H2Connection.getConnection();
            PreparedStatement pst = connection.prepareStatement(select);
            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                odontologo = crearObjetoOdontologo(rst);
                odontologosLista.add(odontologo);
            }
            //nos falto el log aca
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
        return odontologosLista;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                odontologo = crearObjetoOdontologo(rs);
            }
            LOGGER.info("Se ha encontrado el odontologo con id {}: {}", id, odontologo);

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

        return odontologo;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        String delete = "DELETE FROM ODONTOLOGOS WHERE ID = ?";

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, id);
            pst.execute();

            connection.commit();
            LOGGER.info("El odontologo con id: {} fue eliminado de la base de datos.",id);

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
    public Odontologo modificar(Odontologo odontologo) {
        return null;
    }
    private Odontologo crearObjetoOdontologo(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        int matricula = rs.getInt(2);
        String nombre = rs.getString(3);
        String apellido = rs.getString(4);

        Odontologo odontologo = new Odontologo(id,matricula,nombre,apellido);
        return odontologo;
    }
}


