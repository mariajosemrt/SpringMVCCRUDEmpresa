package com.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.entities.Departamento;

@Repository
public interface DepartamentoDao extends JpaRepository<Departamento, Integer> {
    
}
