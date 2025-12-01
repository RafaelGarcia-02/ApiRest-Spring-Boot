package com.example.exam.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String fecha;
    int coste;

    @ManyToMany
    @JsonIgnore
    private List<Pieza> piezas;

    @ManyToOne
    private Vehiculo vehiculo;

    public Reparacion(int id){
        this.id = id;
    }
}
