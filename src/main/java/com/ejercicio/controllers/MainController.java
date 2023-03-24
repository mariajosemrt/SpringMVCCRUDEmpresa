package com.ejercicio.controllers;

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
import org.springframework.web.servlet.View;

import com.ejercicio.entities.Departamento;
import com.ejercicio.entities.Empleado;
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
    // @PostMapping("/altaModificacionEmpleado")
    // public String altaEmpleado(@ModelAttribute Empleado emplado,
    //         @RequestParam(name = "numerosTelefonos") String telefonosRecibidos) {


    //         }
}
