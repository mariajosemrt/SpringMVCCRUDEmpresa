package com.ejercicio.services;

import java.util.List;

import com.ejercicio.entities.Empleado;

public interface EmpleadoService {
    
    public List<Empleado> findAll();
    public Empleado findById(int idEmpleado);
    public void save(Empleado empleado);
    public void deleteById(int idEmpleado);
    public void delete(Empleado empleado);

/** No es necesario un metodo update pq el save inserta o actualiza, en dependencia
* de que el idEstudiante exista o no, si no existe te lo crea, y si 
* existe actualiza la informacion.*/
}
