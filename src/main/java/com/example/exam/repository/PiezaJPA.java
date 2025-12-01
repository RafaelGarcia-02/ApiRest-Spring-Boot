package com.example.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.models.Pieza;

public interface PiezaJPA  extends JpaRepository<Pieza, Integer>{
    List<Pieza> findByNombre(String nombre);
    
} 
