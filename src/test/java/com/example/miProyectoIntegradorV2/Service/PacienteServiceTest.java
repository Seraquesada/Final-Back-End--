package com.example.miProyectoIntegradorV2.Service;


import com.example.miProyectoIntegradorV2.Entities.Domicilio;
import com.example.miProyectoIntegradorV2.Entities.Paciente;
import com.example.miProyectoIntegradorV2.Entities.Paciente;
import com.example.miProyectoIntegradorV2.Excepciones.BadRequestException;
import com.example.miProyectoIntegradorV2.Excepciones.ConflictEmailException;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void save() throws ConflictEmailException, BadRequestException {

        Paciente paciente= new Paciente();
        paciente.setId(1L);
        paciente.setNombre("a");
        paciente.setApellido("a");
        paciente.setEmail("a");
        paciente.setFecha(LocalDate.of(2022,8,2));
        paciente.setDni(23);

        Domicilio  domicilio = new Domicilio();
        domicilio.setId(1L);
        domicilio.setCalle("aa");
        domicilio.setLocalidad("sss");
        domicilio.setNumero(2);
        domicilio.setProvincia("aaa");
        paciente.setDomicilio(domicilio);
        pacienteService.save(paciente);


        Paciente paciente3 = new Paciente();
        paciente3.setId(2L);
        paciente3.setNombre("b");
        paciente3.setApellido("b");
        paciente3.setEmail("bbb");
        paciente3.setFecha(LocalDate.of(2022,8,2));
        paciente3.setDni(23);
        Domicilio domicilio2 = new Domicilio();
        domicilio.setId(2L);
        domicilio.setCalle("aa");
        domicilio.setLocalidad("sss");
        domicilio.setNumero(2);
        domicilio.setProvincia("aaa");
        paciente3.setDomicilio(domicilio2);

        pacienteService.save(paciente3);
        assertTrue(pacienteService.findById(2L) != null);

    }

    @Test
    @Order(2)
    void findById() throws BadRequestException {
        Optional<Paciente> paciente = pacienteService.findById(1L);
        assertEquals(1,paciente.get().getId());
    }

    @Test
    @Order(3)
    void buscarXEmail() throws BadRequestException {
        Optional<Paciente> paciente = pacienteService.buscarXEmail("a");
        assertEquals("a",paciente.get().getEmail());
    }

    @Test
    @Order(4)
    void findAll() {
        List<Paciente> pacientes =pacienteService.findAll() ;
        assertEquals(2,pacientes.size());
    }

    @Test
    @Order(5)
    void update() throws BadRequestException {
        Paciente paciente = new Paciente();
        paciente.setId(2L);
        paciente.setNombre("ccc");
        paciente.setApellido("ccc");
        paciente.setEmail("ccc");
        paciente.setFecha(LocalDate.of(2022,8,2));
        paciente.setDni(23);
        Domicilio  domicilio = new Domicilio();
        domicilio.setId(2L);
        domicilio.setCalle("aa");
        domicilio.setLocalidad("sss");
        domicilio.setNumero(2);
        domicilio.setProvincia("aaa");
        paciente.setDomicilio(domicilio);

        pacienteService.update(paciente);
        assertEquals("ccc",pacienteService.findById(2L).get().getNombre());
    }

    @Test
    @Order(6)
    void delete() throws BadRequestException {
        pacienteService.delete(1l);
        assertEquals(1,pacienteService.findAll().size());
    }
}