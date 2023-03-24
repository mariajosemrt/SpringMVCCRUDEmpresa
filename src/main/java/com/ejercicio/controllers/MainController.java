package com.ejercicio.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
             @RequestParam(name = "correos") String correosRecibidos) {

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

    /** MUESTRA EL FORMULARIO PARA ACTUALIZAR UN ESTUDIANTE */
    // @GetMapping("/frmActualizar/{id}")
}
