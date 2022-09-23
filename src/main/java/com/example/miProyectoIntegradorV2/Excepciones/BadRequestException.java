package com.example.miProyectoIntegradorV2.Excepciones;

public class BadRequestException extends Exception{
    public BadRequestException (String mensaje){
        super(mensaje);
    }
}
