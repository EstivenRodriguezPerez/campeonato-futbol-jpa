package com.campeonato.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campeonato.app.entidades.Asociacion;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepositorio extends JpaRepository<Asociacion, Long> {
}