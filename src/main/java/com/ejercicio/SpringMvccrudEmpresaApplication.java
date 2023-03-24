package com.ejercicio;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ejercicio.entities.Correo;
import com.ejercicio.entities.Departamento;
import com.ejercicio.entities.Empleado;
import com.ejercicio.entities.Telefono;
import com.ejercicio.entities.Empleado.Genero;
import com.ejercicio.services.CorreoService;
import com.ejercicio.services.DepartamentoService;
import com.ejercicio.services.EmpleadoService;
import com.ejercicio.services.TelefonoService;

@SpringBootApplication
public class SpringMvccrudEmpresaApplication implements CommandLineRunner {

	@Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private CorreoService correoService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvccrudEmpresaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		departamentoService.save(
			Departamento.builder()
			.nombre("Recursos Humanos")
			.build()
		);

		departamentoService.save(
			Departamento.builder()
			.nombre("Informática")
			.build()
		);

		empleadoService.save(
			Empleado.builder()
			.id(1)
			.nombre("Elisabet")
			.apellidos("Agulló García")
			.fechaAlta(LocalDate.of(2000, Month.APRIL, 12))
			.genero(Genero.MUJER)
			.departamento(departamentoService.findById(1))
			.build()
		);

		telefonoService.save(
			Telefono.builder()
			.id(2)
			.numero("663994005")
			.empleado(empleadoService.findById(1))
			.build()
		);

		telefonoService.save(
			Telefono.builder()
			.id(2)
			.numero("666329021")
			.empleado(empleadoService.findById(1))
			.build()
		);

		correoService.save(
			Correo.builder()
			.id(1)
			.email("eli@gmail.com")
			.empleado(empleadoService.findById(1))
			.build()
		);
	}

}
