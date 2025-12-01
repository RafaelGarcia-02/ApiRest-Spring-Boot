package com.example.exam.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Vehiculo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    String marca;
    String modelo;

    @OneToMany(mappedBy = "vehiculo",cascade =CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    List<Reparacion> reparaciones;
    
    public Vehiculo(int id){
        this.id=id;
    }
}
