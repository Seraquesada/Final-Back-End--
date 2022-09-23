package com.example.miProyectoIntegradorV2.Service;


import com.example.miProyectoIntegradorV2.Entities.Paciente;
import com.example.miProyectoIntegradorV2.Excepciones.BadRequestException;
import com.example.miProyectoIntegradorV2.Excepciones.ConflictEmailException;
import com.example.miProyectoIntegradorV2.Repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository){
        this.pacienteRepository= pacienteRepository;
    }
    @Autowired
    ObjectMapper mapper;

    public void save(Paciente paciente) throws ConflictEmailException {
        Optional<Paciente> pacienteVerificar = pacienteRepository.findByEmail(paciente.getEmail());
        if (pacienteVerificar.isPresent()){
            throw new ConflictEmailException("Ya existe un pacienteEntity con ese mail");
        }else{
              pacienteRepository.save(paciente);
        }

    }
    public Optional<Paciente> findById(Long id)throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            return pacienteBuscado;
        }
        else {
            throw new BadRequestException("No existe el paciente con ese id: " + id);
        }
    }
    public Optional<Paciente> buscarXEmail(String email) throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteRepository.findByEmail(email);
        if(pacienteBuscado.isPresent()){
            return pacienteBuscado;
        }
        else {
            throw new BadRequestException("No existe el paciente con ese email:  "+ email);
        }
     }
    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }
    public void update(Paciente paciente) throws BadRequestException{
        if(pacienteRepository.findById(paciente.getId()).isPresent()){
            pacienteRepository.save(paciente);
        }else {
            throw new BadRequestException("No se pudo actualizar el paciente correctamente");
        }
    }
    public void delete(Long id) throws BadRequestException{
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            pacienteRepository.deleteById(id);
        }
        else {
            throw new BadRequestException("No existe el paciente con ese id: " + id);
        }

    }
}
