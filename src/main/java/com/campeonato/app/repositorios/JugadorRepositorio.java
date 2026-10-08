package com.campeonato.app.repositorios;


import com.campeonato.app.entidades.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepositorio extends JpaRepository<Jugador, Long> {
}