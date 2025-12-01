package com.example.exam.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam.dto.ReparacionDto;
import com.example.exam.models.Reparacion;

import com.example.exam.service.ReparacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/private/reparacion")
@RequiredArgsConstructor

public class ReparacionController {

    
    final ReparacionService service;

   
    @GetMapping("/")
    public ResponseEntity<List<Reparacion>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reparacion> getByID(@PathVariable int id ){
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/get{fecha}")
    public ResponseEntity<List<Reparacion>> getByFecha(@PathVariable String fecha){
        return ResponseEntity.ok(service.getByFecha(fecha));
    }

    @PostMapping("/add")
    public ResponseEntity<Reparacion> add(@RequestBody ReparacionDto dto){
        return ResponseEntity.ok(service.add(dto));
    }
    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(service.delete(id));
    }
    @PostMapping("/modify{id}")
    public ResponseEntity<Reparacion> modify(@PathVariable int id,@RequestBody ReparacionDto dto){
        return ResponseEntity.ok(service.modify(id, dto));
    }


    
}
