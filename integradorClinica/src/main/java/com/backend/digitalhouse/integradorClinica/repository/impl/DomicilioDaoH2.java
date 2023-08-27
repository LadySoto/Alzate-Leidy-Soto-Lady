package com.backend.digitalhouse.integradorClinica.repository.impl;

import com.backend.digitalhouse.integradorClinica.entity.Domicilio;
import com.backend.digitalhouse.integradorClinica.repository.H2Connection;
import com.backend.digitalhouse.integradorClinica.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomicilioDaoH2.class);

    @Override
    public Domicilio registrar(Domicilio domicilio) {
        Connection connection = null;
        String insert = "INSERT INTO DOMICILIOS (CALLE, NUMERO,LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
        Domicilio domicilio1 = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, domicilio.getCalle());
            pst.setInt(2, domicilio.getNumero());
            pst.setString(3, domicilio.getLocalidad());
            pst.setString(4, domicilio.getProvincia());
            pst.execute();

            connection.commit();

            domicilio1 = new Domicilio(domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(),domicilio.getProvincia());
            ResultSet key = pst.getGeneratedKeys();
            while (key.next()) {
                domicilio1.setId(key.getInt(1));
            }
            LOGGER.info("Domicilio registrado: " + domicilio1);

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
        return domicilio1;
    }

    @Override
    public List<Domicilio> listar() {
        Connection connection = null;
        Domicilio domicilio = null;
        List<Domicilio> domiciliosLista = new ArrayList<>();
        String select = "SELECT * FROM DOMICILIOS";

        try {
            connection = H2Connection.getConnection();
            PreparedStatement pst = connection.prepareStatement(select);
            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                domicilio = crearObjetoDomicilio(rst);
                domiciliosLista.add(domicilio);
            }
            LOGGER.info("Se generó la lista de domicilios: {}", domiciliosLista);

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
        return domiciliosLista;
    }

    @Override
    public Domicilio buscarPorId(int id) {
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                domicilio = crearObjetoDomicilio(rs);
            }
            LOGGER.info("Se ha encontrado el domicilio con id {}: {}", id, domicilio);

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

        return domicilio;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        String delete = "DELETE FROM DOMICILIOS WHERE ID = ?";

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, id);
            pst.execute();

            connection.commit();
            LOGGER.info("El domicilio con id: {} fue eliminado de la base de datos.",id);

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
    public Domicilio modificar(Domicilio domicilio) {
        return null;
    }


    private Domicilio crearObjetoDomicilio(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String calle = rs.getString(2);
        int numero = rs.getInt(3);
        String localidad = rs.getString(4);
        String provincia = rs.getString(5);

        Domicilio domicilio = new Domicilio(id,calle,numero,localidad, provincia);
        return domicilio;
    }
}
