package com.campeonato.app.repositorios;

import com.campeonato.app.entidades.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepositorio extends MongoRepository<Club, Long> {
}