package com.backend.digitalhouse.integradorClinica;

import com.backend.digitalhouse.integradorClinica.repository.H2Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class IntegradorClinicaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntegradorClinicaApplication.class);
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(IntegradorClinicaApplication.class, args);
		H2Connection.create();
		LOGGER.info("ClinicaOdontologica is now running...");
	}

}