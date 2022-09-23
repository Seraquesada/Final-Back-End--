package com.example.miProyectoIntegradorV2.Service;


import com.example.miProyectoIntegradorV2.Entities.*;
import com.example.miProyectoIntegradorV2.Excepciones.BadRequestException;
import com.example.miProyectoIntegradorV2.Repository.OdontologoRepository;
import com.example.miProyectoIntegradorV2.Repository.PacienteRepository;
import com.example.miProyectoIntegradorV2.Repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;
    private final OdontologoRepository odontologoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

    public TurnoService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public TurnoDto save(TurnoDto turno)  {
        Turno turnoEntity= new Turno();
        turnoEntity.setFecha(turno.getFecha());

        Paciente paciente= new Paciente();
        paciente.setId(turno.getPaciente_id());
        Odontologo odontologo= new Odontologo();
        odontologo.setId(turno.getOdontologo_id());

        turnoEntity.setPaciente(paciente);
        turnoEntity.setOdontologo(odontologo);

        Turno turnoGuardado=turnoRepository.save(turnoEntity);
        TurnoDto turnoADevolver= new TurnoDto();
        turnoADevolver.setFecha(turnoGuardado.getFecha());
        turnoADevolver.setOdontologo_id(turnoGuardado.getOdontologo().getId());
        turnoADevolver.setPaciente_id(turnoGuardado.getPaciente().getId());
        turnoADevolver.setId(turnoGuardado.getId());

        return turnoADevolver;
    }

    public Optional<TurnoDto> findById(Long id) throws BadRequestException {
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        Optional<TurnoDto> turnoDtoBuscado = Optional.empty();
        if (turnoBuscado.isPresent()){
            Turno turnoRealBuscado=turnoBuscado.get();
            TurnoDto turnoDtoADevolver= new TurnoDto();
            turnoDtoADevolver.setId(turnoRealBuscado.getId());
            turnoDtoADevolver.setFecha(turnoRealBuscado.getFecha());
            turnoDtoADevolver.setPaciente_id(turnoRealBuscado.getPaciente().getId());
            turnoDtoADevolver.setOdontologo_id(turnoRealBuscado.getOdontologo().getId());
            turnoDtoBuscado=Optional.of(turnoDtoADevolver);
        }else {
            throw new BadRequestException("No existe turno con ese id: " +id);
        }
        return turnoDtoBuscado;
    }

    public List<TurnoDto> findAll(){
        List<Turno> turnos= turnoRepository.findAll();
        List<TurnoDto> turnosDto = new ArrayList<>();

        for (Turno turno : turnos) {
            TurnoDto turnoDtoADevolver= new TurnoDto();
            turnoDtoADevolver.setId(turno.getId());
            turnoDtoADevolver.setFecha(turno.getFecha());
            turnoDtoADevolver.setPaciente_id(turno.getPaciente().getId());
            turnoDtoADevolver.setOdontologo_id(turno.getOdontologo().getId());
            turnosDto.add(turnoDtoADevolver);
        }
        return turnosDto;
    }

    public TurnoDto actualizar(TurnoDto turno) throws BadRequestException {
        Optional<TurnoDto> turnoBuscado = findById(turno.getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo_id());
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente_id());

        if (turnoBuscado.isPresent()){
            if (odontologo.isPresent() && paciente.isPresent()){
                Turno turnoEntity= new Turno();
                turnoEntity.setId(turno.getId());
                turnoEntity.setFecha(turno.getFecha());

                Paciente paciente2 = new Paciente();
                paciente2.setId(turno.getPaciente_id());
                Odontologo odontologo2 = new Odontologo();
                odontologo2.setId(turno.getOdontologo_id());

                turnoEntity.setPaciente(paciente2);
                turnoEntity.setOdontologo(odontologo2);

                Turno turnoGuardado=turnoRepository.save(turnoEntity);
                //alternativa A
                TurnoDto turnoADevolver= new TurnoDto();
                turnoADevolver.setFecha(turnoGuardado.getFecha());
                turnoADevolver.setOdontologo_id(turnoGuardado.getOdontologo().getId());
                turnoADevolver.setPaciente_id(turnoGuardado.getPaciente().getId());
                turnoADevolver.setId(turnoGuardado.getId());

                return turnoADevolver;

        }else {
            throw new BadRequestException("No se puedo agregar el turno, ya sea que el odontologo no existe:" +
                    odontologo.get().getNombre() + " o el paciente no exite: " + paciente.get().getNombre());
             }
        } else{
            throw new BadRequestException("No existe ese turno");
        }
    }

    public void delete(Long id) throws BadRequestException {

        if (turnoRepository.findById(id).isPresent()){
            turnoRepository.deleteById(id);
        }else {
            throw new BadRequestException("No existe el turno con ese id: "+ id);
        }

    }
}
