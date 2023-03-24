package com.ejercicio.services;

import java.util.List;

import com.ejercicio.entities.Empleado;
import com.ejercicio.entities.Telefono;

public interface TelefonoService {

    public List<Telefono> findAll();
    public Telefono findById(int idTelefono); 
    public void save(Telefono telefono); 
    public void deleteById(int idTelefono);

    public void deleteByEmpleado(Empleado empleado); 

    //Para detalles, es lo mismo que lo que tenemos en TelefonoDao
    public List<Telefono> findByEmpleado(Empleado empleado);
    
}
