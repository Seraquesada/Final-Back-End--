package com.example.miProyectoIntegradorV2.controller;


import com.example.miProyectoIntegradorV2.Entities.Odontologo;
import com.example.miProyectoIntegradorV2.Excepciones.BadRequestException;
import com.example.miProyectoIntegradorV2.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<?> registrarOdontolog(@RequestBody Odontologo odontologo){
        odontologoService.save(odontologo);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Odontologo> findAllPacientes() {
        return odontologoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Odontologo> findById(@PathVariable("id") Long id) throws BadRequestException{
        return odontologoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id)throws BadRequestException {
        odontologoService.delete(id);
        return ResponseEntity.ok("El odontologo con la id " + id + " ha sido eliminado");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Odontologo odontologo)throws BadRequestException {
        odontologoService.update(odontologo);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
