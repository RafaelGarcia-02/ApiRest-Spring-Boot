package com.example.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.models.Vehiculo;

public interface VehiculoJPA extends JpaRepository<Vehiculo,Integer> {

    List<Vehiculo> findByMarca(String marca);
    List<Vehiculo> findByModelo(String modelo);
    
}
