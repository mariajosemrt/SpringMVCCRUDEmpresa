package com.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.entities.Empleado;

@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
    
}
