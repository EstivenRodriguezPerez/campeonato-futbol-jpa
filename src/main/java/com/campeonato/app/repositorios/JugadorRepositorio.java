package com.campeonato.app.repositorios;

import com.campeonato.app.entidades.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepositorio extends MongoRepository<Jugador, Long> {
}