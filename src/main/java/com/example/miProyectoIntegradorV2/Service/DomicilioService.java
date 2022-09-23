package com.example.miProyectoIntegradorV2.Service;


import com.example.miProyectoIntegradorV2.Entities.Domicilio;
import com.example.miProyectoIntegradorV2.Repository.DomicilioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    private final DomicilioRepository domicilioRepository;

    public DomicilioService(DomicilioRepository domicilioRepository){
        this.domicilioRepository= domicilioRepository;
    }

    public Optional<Domicilio> buscarDomicilioXId(Long id){
        return domicilioRepository.findById(id);
    }
    //public Domicilio buscarXCriterio(String criterio) {
      //  return domicilioRepository.(criterio);
   // }

    public List<Domicilio> buscarTodosDomicilios(){
        return domicilioRepository.findAll();
    }
    public Domicilio guardarDomicilio(Domicilio domicilio){
        return domicilioRepository.save(domicilio);
    }

    //public Domicilio actualizar(Domicilio domicilio) {
   //     return domicilioRepository.ar(domicilio);
    //}

    public void eliminar(Long id) {
        domicilioRepository.deleteById(id);
    }

}
