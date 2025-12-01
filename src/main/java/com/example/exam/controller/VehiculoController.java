package com.example.exam.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam.dto.VehiculoDto;
import com.example.exam.models.Vehiculo;
import com.example.exam.service.VehiculoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/private/vehiculos")
@RequiredArgsConstructor
@CrossOrigin(originPatterns ="[*]")
public class VehiculoController {
    private final VehiculoService service;

    @GetMapping("/")
    public ResponseEntity<List<Vehiculo>> getAll(){
        return ResponseEntity.ok(service.getAll());
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getById(@PathVariable int id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) String modelo
    ){
        if(marca!=null){
            return ResponseEntity.ok(service.getByMarca(marca));
        }
        else if(modelo!=null){
            return ResponseEntity.ok(service.getByModelo(modelo));
        }
        else{
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Vehiculo> add(@RequestBody VehiculoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(dto));
    }
    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(service.delete(id));

    }
    @PostMapping("/modify{id}")
    public ResponseEntity<Vehiculo> modify(@PathVariable int id,@RequestBody VehiculoDto dto){
        return ResponseEntity.ok(service.modify(dto, id));
    }

    
}
