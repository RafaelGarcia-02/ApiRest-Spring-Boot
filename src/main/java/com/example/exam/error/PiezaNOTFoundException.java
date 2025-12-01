package com.example.exam.error;

public class PiezaNOTFoundException extends RuntimeException {
    public PiezaNOTFoundException(int id){
        super("No se ha encontrado la pieza con id: "+id);
    }
    public PiezaNOTFoundException(String nombre){
        super("No se ha encontrado la pieza con nombre: "+nombre);
    }
    
    
}
