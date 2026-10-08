package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Club;
import com.campeonato.app.repositorios.ClubRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubes")
@CrossOrigin(origins = "*")
public class ClubRestController {

    @Autowired
    private ClubRepositorio clubRepo;

    @GetMapping
    public List<Club> listar() {
        return clubRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> obtenerPorId(@PathVariable Long id) {
        return clubRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Club> crear(@RequestBody Club club) {
        return new ResponseEntity<>(clubRepo.save(club), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> actualizar(@PathVariable Long id, @RequestBody Club club) {
        if (!clubRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        club.setId(id);
        return ResponseEntity.ok(clubRepo.save(club));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!clubRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clubRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}