package com.example.miProyectoIntegradorV2.Service;


import com.example.miProyectoIntegradorV2.Entities.Odontologo;
import com.example.miProyectoIntegradorV2.Excepciones.BadRequestException;
import com.example.miProyectoIntegradorV2.Repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private final OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository){
        this.odontologoRepository = odontologoRepository;
    }
    public void save(Odontologo odontologo){
        odontologoRepository.save(odontologo);

    }
    public Optional<Odontologo> findById(Long id)throws BadRequestException{
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            return odontologo;
        }
        throw new BadRequestException("No existe un odontologo con ese id: " + id);

    }
    public List<Odontologo> findAll(){
        return odontologoRepository.findAll();
    }
    public void update(Odontologo odontologo)throws BadRequestException {
        if(odontologoRepository.findById(odontologo.getId()).isPresent()){
            odontologoRepository.save(odontologo);
        }else {
            throw new BadRequestException("No se pudo actualizar el odontologo correctamente");
        }
    }
    public void delete(Long id)throws BadRequestException {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            odontologoRepository.deleteById(id);
        }
        else {
            throw new BadRequestException("No existe el paciente con ese id: " + id);
        }
    }
}
