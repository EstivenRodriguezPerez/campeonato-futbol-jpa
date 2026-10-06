package com.campeonato.app.repositorios;

import com.campeonato.app.entidades.Asociacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepositorio extends MongoRepository<Asociacion, Long> {
}