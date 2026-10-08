package com.campeonato.app.repositorios;


import com.campeonato.app.entidades.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Long> {
}