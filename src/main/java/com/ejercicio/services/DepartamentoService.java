package com.ejercicio.services;

import java.util.List;

import com.ejercicio.entities.Departamento;

public interface DepartamentoService {
    
    public List<Departamento> findAll();
    public Departamento findById(int idFacultad); 
    public void save(Departamento departamento); 
    public void deleteById(int idDepartamento); 
}
