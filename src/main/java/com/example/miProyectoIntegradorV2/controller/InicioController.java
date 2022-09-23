package com.example.miProyectoIntegradorV2.controller;


import com.example.miProyectoIntegradorV2.Service.OdontologoService;
import com.example.miProyectoIntegradorV2.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Optional;

@Controller
@RequestMapping("/index")
public class InicioController {
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public InicioController(PacienteService pacienteService, OdontologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }


}
