package com.example.exam.error;

public class VehiculoNOtFoundException extends RuntimeException{

    
    public VehiculoNOtFoundException(Integer id ){
        super("No hay un vehiculo con ID =%d" .formatted(id));
    }
    public VehiculoNOtFoundException(String mesaage){
        super("No hay ningun vehiculo con la marca/modelo =" + mesaage);
    }
    public VehiculoNOtFoundException(){
        super("No hay vehiculos con esos requisitos de busqueda");
    }    
}
