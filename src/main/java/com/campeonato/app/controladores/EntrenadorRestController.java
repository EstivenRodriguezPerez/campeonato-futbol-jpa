package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Entrenador;
import com.campeonato.app.repositorios.EntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
public class EntrenadorRestController {

    @Autowired
    private EntrenadorRepositorio entrenadorRepo;

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerPorId(@PathVariable Long id) {
        return entrenadorRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entrenador> crear(@RequestBody Entrenador entrenador) {
        return new ResponseEntity<>(entrenadorRepo.save(entrenador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizar(@PathVariable Long id, @RequestBody Entrenador entrenador) {
        if (!entrenadorRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entrenador.setId(id);
        return ResponseEntity.ok(entrenadorRepo.save(entrenador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!entrenadorRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entrenadorRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}