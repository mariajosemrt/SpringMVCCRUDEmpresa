package com.ejercicio.services;

import java.util.List;

import com.ejercicio.entities.Correo;
import com.ejercicio.entities.Empleado;

public interface CorreoService {
   
    public List<Correo> findAll();
    public Correo findById(int idCorreo); 
    public void save(Correo correo); 
    public void deleteById(int idCorreo);

    public void deleteByEmpleado(Empleado empleado); 

    //Para detalles, es lo mismo que lo que tenemos en CorreoDAO
    public List<Correo> findByEmpleado(Empleado empleado);
}
