package com.campeonato.app.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
@Table(name = "clubes")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entrenador_id", referencedColumnName = "id")
    private Entrenador entrenador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asociacion_id")
    private Asociacion asociacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "club_id")
    private List<Jugador> jugadores;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "club_competicion",
        joinColumns = @JoinColumn(name = "club_id"),
        inverseJoinColumns = @JoinColumn(name = "competicion_id")
    )
    private List<Competicion> competiciones;

    public Club() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    public Asociacion getAsociacion() { return asociacion; }
    public void setAsociacion(Asociacion asociacion) { this.asociacion = asociacion; }
    public List<Jugador> getJugadores() { return jugadores; }
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }
    public List<Competicion> getCompeticiones() { return competiciones; }
    public void setCompeticiones(List<Competicion> competiciones) { this.competiciones = competiciones; }
}