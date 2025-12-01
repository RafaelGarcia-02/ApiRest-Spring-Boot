package com.example.exam.error;

public class ReparacionNOTFoundException extends RuntimeException {
    
    public ReparacionNOTFoundException(Integer id){
        super("No hay un reparacion con ID =%d" .formatted(id));
    }
    public ReparacionNOTFoundException(String mesaaa){
        super(mesaaa);
    }
    public ReparacionNOTFoundException(){
        super("No hay reparaciones con esas caracteristicas");
    }
    
}
