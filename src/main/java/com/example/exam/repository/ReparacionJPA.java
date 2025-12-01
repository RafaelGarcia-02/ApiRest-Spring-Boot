package com.example.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.models.Reparacion;

public interface ReparacionJPA extends JpaRepository<Reparacion,Integer> {
    

    List<Reparacion> findByFecha(String fecha);
}
