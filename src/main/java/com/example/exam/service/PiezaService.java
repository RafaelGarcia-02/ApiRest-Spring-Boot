package com.example.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exam.dto.PiezaDto;
import com.example.exam.error.PiezaNOTFoundException;
import com.example.exam.models.Pieza;
import com.example.exam.repository.PiezaJPA;

import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class PiezaService {

    final PiezaJPA piezaJPA;
    public List<Pieza> getByNombre(String nombre){
        var result = piezaJPA.findByNombre(nombre);
        if(result.isEmpty()) throw new PiezaNOTFoundException(nombre);
        return result;
    }
    public Pieza getById(int id){
        return piezaJPA.findById(id).orElseThrow(()-> new PiezaNOTFoundException(id));
    }
    public List<Pieza> getAll(){
        return piezaJPA.findAll();
    }
    public Pieza add(PiezaDto dto){
        var nuevaPieza = Pieza.builder().nombre(dto.nombre()).coste(dto.coste()).build();
        return piezaJPA.save(nuevaPieza);
    }   
    public Pieza modify(int id , PiezaDto dto){
        return piezaJPA.findById(id).map(pieza -> {
            pieza.setNombre(dto.nombre());
            pieza.setCoste(dto.coste());
            return piezaJPA.save(pieza);
        }).orElseThrow(()-> new PiezaNOTFoundException(id));
    }
    public String delete(int id){
        return piezaJPA.findById(id).map(pieza -> {
            piezaJPA.delete(pieza);
            return "Se ha borrado correctamente";
        }).orElseThrow(()-> new PiezaNOTFoundException(id));
    }
   
}
