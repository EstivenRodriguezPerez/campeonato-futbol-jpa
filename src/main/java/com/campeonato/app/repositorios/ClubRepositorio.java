package com.campeonato.app.repositorios;


import com.campeonato.app.entidades.Club;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ClubRepositorio extends JpaRepository<Club, Long> {
}