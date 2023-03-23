package com.ejercicio.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull (message = "El nombre no puede ser null")
    @Size ( max = 25, min = 2)
    private String nombre;
    private String apellidos;
    
    
    private LocalDate fechaAlta; 
    private Genero genero;

    public enum Genero {
        MUJER, HOMBRE, OTRO 
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    //No hace falta el join, pero lo ponemos pq nos gusta más como se queda
    @JoinColumn(name = "idDepartamento")
    //Un empleado tiene un departamento, asi que creamos una propiedad departamento
    //para relacionar departamento con empleado
    private Departamento departamento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "empleado")
    private List<Telefono> telefonos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "empleado")
    private List<Correo> correos;



    
}
