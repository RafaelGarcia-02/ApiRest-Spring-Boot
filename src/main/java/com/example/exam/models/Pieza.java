package com.example.exam.models;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pieza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nombre;
    int coste;

    @ManyToMany(mappedBy = "piezas")
    @JsonIgnore
    private List<Reparacion> reparaciones;

    public Pieza(int id){
        this.id = id;
    }
}
