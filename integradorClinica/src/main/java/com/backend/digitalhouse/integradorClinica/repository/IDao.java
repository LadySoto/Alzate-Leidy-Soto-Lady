package com.backend.digitalhouse.integradorClinica.repository;

import java.util.List;

public interface IDao <T>{
    T registrar (T t);
    List<T> listar();
    T buscarPorId (int id);
    void eliminar (int id);
    T modificar (T t);
}