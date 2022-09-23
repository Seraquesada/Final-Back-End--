package com.example.miProyectoIntegradorV2.controller;

import com.example.miProyectoIntegradorV2.Entities.TurnoDto;
import com.example.miProyectoIntegradorV2.Excepciones.BadRequestException;
import com.example.miProyectoIntegradorV2.Service.OdontologoService;
import com.example.miProyectoIntegradorV2.Service.PacienteService;
import com.example.miProyectoIntegradorV2.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody TurnoDto turnoDto) throws BadRequestException {
        if (odontologoService.findById(turnoDto.getOdontologo_id()).isPresent()
                && pacienteService.findById(turnoDto.getPaciente_id()).isPresent())
        {
            return ResponseEntity.ok(turnoService.save(turnoDto));
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping()
    public List<TurnoDto> buscarAllTurnos(){
        return turnoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TurnoDto> buscarXId(@PathVariable("id") Long id)throws BadRequestException{
        return turnoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable("id") Long id)throws BadRequestException{
            turnoService.delete(id);
            return ResponseEntity.ok("El turno a sido borrado");
    }

    @PutMapping()
    public ResponseEntity<String> modificarTurno(@RequestBody TurnoDto turno)throws BadRequestException{
        turnoService.actualizar(turno);
        return ResponseEntity.ok("El turno a sido modificado");
    }
}
