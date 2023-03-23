package com.ejercicio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.entities.Correo;
import com.ejercicio.entities.Empleado;

@Repository
public interface CorreoDao extends JpaRepository<Correo, Integer> {
    
    //Para borrar los correos cuando borramos el empleado
    long deleteByEmpleado(Empleado empleado);

    //Para los detalles de empleado
    List<Correo> findByEmpleado(Empleado empleado);
}
