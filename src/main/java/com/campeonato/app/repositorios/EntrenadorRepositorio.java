package com.campeonato.app.repositorios;

import com.campeonato.app.entidades.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepositorio extends MongoRepository<Entrenador, Long> {
}