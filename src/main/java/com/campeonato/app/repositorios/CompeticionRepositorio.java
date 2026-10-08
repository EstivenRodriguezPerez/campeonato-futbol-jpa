package com.campeonato.app.repositorios;


import com.campeonato.app.entidades.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticionRepositorio extends JpaRepository<Competicion, Long> {
}