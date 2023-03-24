package com.ejercicio.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ejercicio.entities.Correo;
import com.ejercicio.entities.Departamento;
import com.ejercicio.entities.Empleado;
import com.ejercicio.entities.Telefono;
import com.ejercicio.services.CorreoService;
import com.ejercicio.services.DepartamentoService;
import com.ejercicio.services.EmpleadoService;
import com.ejercicio.services.TelefonoService;

@Controller
@RequestMapping("/")

public class MainController {

    //Vamos a crear un logger por si en algun momento queremos poner mensajitos
    private static final Logger LOG = Logger.getLogger("MainController");

    //Inyectamos nuestros objetos service
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private CorreoService correoService;

    /** MÉTODO PARA DEVOLVER LISTADO DE EMPLEADOS */
    @GetMapping("/listar")
    public ModelAndView listar() {

            List<Empleado> empleados = empleadoService.findAll();

            ModelAndView mav = new ModelAndView("views/listarEmpleados");
            mav.addObject("empleados", empleados);

        return mav;
    }

    /** MÉTODO PARA MOSTRAR FORMULARIO DE ALTA DE EMPLEADO */
    //No vamos a usar ModelAndView por tener diferentes ejemplos de formas
    //en las que se pueden crear las vistas

    @GetMapping("/frmAltaEmpleado")
    public String formularioAltaEmpleado(Model model) {

        List<Departamento> departamentos = departamentoService.findAll();

        Empleado empleado = new Empleado();

        model.addAttribute("empleado", empleado);
        model.addAttribute("departamentos", departamentos);

        return "views/formularioAltaEmpleado";
    }

    /** METODO QUE RECIBE LOS DATOS PROCEDENTES DEL FORMULARIO */
     @PostMapping("/altaModificacionEmpleado")
     public String altaEmpleado(@ModelAttribute Empleado empleado,
             @RequestParam(name = "numerosTelefonos") String telefonosRecibidos,
             @RequestParam(name = "emailsCorreos") String correosRecibidos) {

        LOG.info("Telefonos recibidos" + telefonosRecibidos);
        LOG.info("Correos recibidos" + correosRecibidos);

        empleadoService.save(empleado);

        List<String> listadoNumerosTelefonos = null;
        List<String> listadoCorreos = null;

        if(telefonosRecibidos != null) {

            String[] arrayTelefonos = telefonosRecibidos.split(";");

            listadoNumerosTelefonos = Arrays.asList(arrayTelefonos);
        }    

        if(listadoNumerosTelefonos != null) {
            telefonoService.deleteByEmpleado(empleado);
            listadoNumerosTelefonos.stream().forEach(numero -> {
                Telefono telefonoObject = Telefono
                .builder()
                .numero(numero)
                .empleado(empleado)
                .build();

            telefonoService.save(telefonoObject);
            });
        }

        if(correosRecibidos != null) {

            String[] arrayCorreos = correosRecibidos.split(",");

            listadoCorreos = Arrays.asList(arrayCorreos);
        }    

        if(listadoCorreos != null) {
            correoService.deleteByEmpleado(empleado);
            listadoCorreos.stream().forEach(correo -> {
                Correo correoObject = Correo
                .builder()
                .email(correo)
                .empleado(empleado)
                .build();

            correoService.save(correoObject);
            });
        }

        return "redirect:/listar";

    }

    /** MUESTRA EL FORMULARIO PARA ACTUALIZAR UN EMPLEADO */
    @GetMapping("/frmActualizar/{id}")
    public String frmActualizarEmpleado(@PathVariable(name = "id") 
    int idEmpleado, Model model) {

        Empleado empleado = empleadoService.findById(idEmpleado);

        List<Telefono> todosTelefonos = telefonoService.findAll();

        List<Telefono> telefonosDelEmpleado = todosTelefonos.stream()
            .filter(telefono -> telefono.getEmpleado().getId() == idEmpleado)
            .collect(Collectors.toList());

        String numerosDeTelefono = telefonosDelEmpleado.stream()
            .map(telefono -> telefono.getNumero())
            .collect(Collectors.joining(";"));

        
        List<Correo> todosCorreos = correoService.findAll();

        List<Correo> correosDelEmpleado = todosCorreos.stream()
            .filter(c -> c.getEmpleado().getId() == idEmpleado)
            .collect(Collectors.toList());

        String emailsDeCorreo = correosDelEmpleado.stream()
            .map(c -> c.getEmail())
            .collect(Collectors.joining(","));

        List<Departamento> departamentos = departamentoService.findAll();

        model.addAttribute("empleado", empleado);
        model.addAttribute("telefonos", numerosDeTelefono);
        model.addAttribute("correos", emailsDeCorreo);
        model.addAttribute("departamentos", departamentos); 

        return "views/formularioAltaEmpleado";

    }

    /** BORRA EL EMPLEADO*/
    @GetMapping("/borrar/{id}")
    public String borrarEmpleado(@PathVariable(name = "id") int idEmpleado) {

        empleadoService.delete(empleadoService.findById(idEmpleado));

        return "redirect:/listar";
    }

    /** MUESTRA LOS DETALLES DEL EMPLEADO */
    @GetMapping("/detalles/{id}")
    public String empleadoDetails(@PathVariable(name = "id") int id, Model model) {

        Empleado empleado = empleadoService.findById(id);

        List<Telefono> telefonos = telefonoService.findByEmpleado(empleado);

        List<String> numerosTelefono = telefonos.stream()
        .map(t -> t.getNumero()).toList();

        List<Correo> correos = correoService.findByEmpleado(empleado);

        List<String> emailsCorreo = correos.stream()
        .map(c -> c.getEmail()).toList();

        model.addAttribute("telefonos", numerosTelefono);
        model.addAttribute("correos", emailsCorreo);
        model.addAttribute("empleado", empleado);

        return "views/detallesEmpleado";
    }
    
}
