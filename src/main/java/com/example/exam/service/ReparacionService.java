package com.example.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exam.dto.ReparacionDto;
import com.example.exam.error.ReparacionNOTFoundException;
import com.example.exam.error.VehiculoNOtFoundException;
import com.example.exam.models.Reparacion;
import com.example.exam.models.Vehiculo;
import com.example.exam.repository.ReparacionJPA;
import com.example.exam.repository.VehiculoJPA;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ReparacionService {
    final ReparacionJPA reparacionJPA;
    final VehiculoJPA vehiculoJPA;

    public List<Reparacion> getAll(){
        return  reparacionJPA.findAll();
        
    }
    public List<Reparacion> getByFecha(String fecha){
        var result = reparacionJPA.findByFecha(fecha);
        if(result.isEmpty()) throw new ReparacionNOTFoundException(fecha);
        return result;
    
    }
    public Reparacion getById(int id){
        return reparacionJPA.findById(id).orElseThrow(() -> new ReparacionNOTFoundException(id));
    }
     public Vehiculo getVehiculoById(int id){
        return vehiculoJPA.findById(id).orElseThrow(()-> new VehiculoNOtFoundException(id));
    }

    public Reparacion add(ReparacionDto dto){
        var vehiculo = this.getVehiculoById(dto.id_vehiculo());
        var nuevoReparacion = Reparacion.builder().fecha(dto.fecha()).coste(dto.coste()).vehiculo(vehiculo).build();
        return nuevoReparacion;
    }
    
    public Reparacion modify(int id , ReparacionDto dto){
        var vehiculo = this.getVehiculoById(dto.id_vehiculo());
        return reparacionJPA.findById(id).map(reparacion -> {
            reparacion.setFecha(dto.fecha());
            reparacion.setCoste(dto.coste());
            reparacion.setVehiculo(vehiculo);
            return reparacionJPA.save(reparacion);
        }).orElseThrow(()-> new ReparacionNOTFoundException(id));
    }
    public Reparacion delete(int id ){
        return reparacionJPA.findById(id).map(reparacion -> {
            reparacionJPA.delete(reparacion);
            return reparacion; 

        }).orElseThrow(()-> new ReparacionNOTFoundException(id));
    }
}
