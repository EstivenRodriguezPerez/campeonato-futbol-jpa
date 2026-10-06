package com.campeonato.app.repositorios;

import com.campeonato.app.entidades.Competicion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticionRepositorio extends MongoRepository<Competicion, Long> {
}