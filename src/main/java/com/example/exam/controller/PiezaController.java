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

import com.example.exam.dto.PiezaDto;
import com.example.exam.models.Pieza;
import com.example.exam.service.PiezaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/private/piezas")
@RequiredArgsConstructor

public class PiezaController {
    final PiezaService service;
    
    @GetMapping("/")
    public ResponseEntity<List<Pieza>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/get{id}")
    public ResponseEntity<Pieza> getById(@PathVariable int id){
        return ResponseEntity.ok( service.getById(id));
    }
    @GetMapping("/getNombre{nombre}")
    public ResponseEntity<List<Pieza>> getByNombre(String nombre){
        return ResponseEntity.ok(service.getByNombre(nombre));
    }
    @PostMapping("/add")
    public ResponseEntity<Pieza> add(@PathVariable PiezaDto dto){
        return ResponseEntity.ok(service.add(dto));
    }
    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/modify{id}")
    public ResponseEntity<?> modify(@PathVariable int id,@RequestBody PiezaDto dto){
        return ResponseEntity.ok(service.modify(id, dto));
    }
    
}
