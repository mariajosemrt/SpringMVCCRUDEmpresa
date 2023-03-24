package com.ejercicio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejercicio.dao.DepartamentoDao;
import com.ejercicio.entities.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoDao departamentoDao;

    @Override
    public List<Departamento> findAll() {
        return departamentoDao.findAll();
    }

    @Override
    public Departamento findById(int idFacultad) {
        return departamentoDao.findById(idFacultad).get();
    }

    @Override
    @Transactional
    public void save(Departamento departamento) {
        departamentoDao.save(departamento);
    }

    @Override
    @Transactional
    public void deleteById(int idDepartamento) {
        departamentoDao.deleteById(idDepartamento);
    }
    
}
