package com.ejercicio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio.entities.Empleado;
import com.ejercicio.entities.Telefono;

public interface TelefonoDao extends JpaRepository<Telefono, Integer> {

    //Para borrar los telefonos cuando borremos el empleado
    long deleteByEmpleado(Empleado empleado);

    //Para los detalles de empleado
    List<Telefono> findByEmpleado(Empleado empleado);
    
}
