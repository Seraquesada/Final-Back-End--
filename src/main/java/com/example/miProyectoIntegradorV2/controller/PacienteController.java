package com.example.miProyectoIntegradorV2.controller;


import com.example.miProyectoIntegradorV2.Entities.Paciente;
import com.example.miProyectoIntegradorV2.Excepciones.BadRequestException;
import com.example.miProyectoIntegradorV2.Excepciones.ConflictEmailException;
import com.example.miProyectoIntegradorV2.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<?> registrarPaciente(@RequestBody Paciente paciente) throws ConflictEmailException {
        pacienteService.save(paciente);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Paciente> findAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Paciente> findById(@PathVariable("id") Long id)throws BadRequestException {
        return pacienteService.findById(id);
    }

    @GetMapping("/email/{email}")
    public Optional<Paciente> findbyEmail(@PathVariable("email") String email)throws BadRequestException {
        return pacienteService.buscarXEmail(email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id)throws BadRequestException {
            pacienteService.delete(id);
            return ResponseEntity.ok("El paciente con el id: "+id+ " a sido borrado");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Paciente paciente)throws BadRequestException {
        pacienteService.update(paciente);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}