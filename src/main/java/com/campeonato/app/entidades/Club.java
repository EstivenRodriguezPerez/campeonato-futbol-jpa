package com.campeonato.app.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "clubes")
public class Club {
    public static final String SEQUENCE_NAME = "clubes_sequence";

    @Id
    private Long id;
    private String nombre;

    // Uno a Uno
    @DocumentReference
    private Entrenador entrenador;

    // Uno a Muchos
    @DocumentReference
    private List<Jugador> jugadores = new ArrayList<>();

    // Muchos a Uno
    @DocumentReference
    private Asociacion asociacion;

    // Muchos a Muchos
    @DocumentReference
    private List<Competicion> competiciones = new ArrayList<>();

    public Club() {}

    public Club(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

    public List<Jugador> getJugadores() { return jugadores; }
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }

    public Asociacion getAsociacion() { return asociacion; }
    public void setAsociacion(Asociacion asociacion) { this.asociacion = asociacion; }

    public List<Competicion> getCompeticiones() { return competiciones; }
    public void setCompeticiones(List<Competicion> competiciones) { this.competiciones = competiciones; }
}