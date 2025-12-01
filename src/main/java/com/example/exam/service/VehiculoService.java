package com.example.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exam.dto.VehiculoDto;
import com.example.exam.error.VehiculoNOtFoundException;
import com.example.exam.models.Vehiculo;
import com.example.exam.repository.VehiculoJPA;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class VehiculoService {
    final VehiculoJPA vehiculoJPA;

    public List<Vehiculo> getAll(){
        var result = vehiculoJPA.findAll();
        return result;
    }
    public Vehiculo getById(int id){
        return vehiculoJPA.findById(id).orElseThrow(()-> new VehiculoNOtFoundException(id));
    }

    public List<Vehiculo> getByMarca(String marca){
        var result= vehiculoJPA.findByMarca(marca);
        if(result.isEmpty()) throw new VehiculoNOtFoundException(marca);
        return result;
    }
    public List<Vehiculo> getByModelo(String modelo){
        var result = vehiculoJPA.findByModelo(modelo);
        if(result.isEmpty()) throw new VehiculoNOtFoundException(modelo);
        return result;
    }

    public Vehiculo add(VehiculoDto dto){
        var nuevoVehiculo = Vehiculo.builder().marca(dto.marca()).modelo(dto.modelo()).build();
        return vehiculoJPA.save(nuevoVehiculo);
    }

    public Vehiculo modify(VehiculoDto dto ,int id  ){
        return vehiculoJPA.findById(id).map(vehiculo -> {
            vehiculo.setMarca(dto.marca());
            vehiculo.setModelo(dto.modelo());
            
        
            return vehiculoJPA.save(vehiculo);
        }).orElseThrow(()-> new VehiculoNOtFoundException(id));
    }

    public String delete(int id){

        return vehiculoJPA.findById(id).map(vehiculo -> {
            vehiculoJPA.delete(vehiculo);
            return "Se ha borrado correctamente";
        }).orElseThrow(()-> new VehiculoNOtFoundException(id));


    }

    
}
