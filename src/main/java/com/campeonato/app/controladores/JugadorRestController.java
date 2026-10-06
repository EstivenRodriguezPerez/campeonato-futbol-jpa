package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Jugador;
import com.campeonato.app.repositorios.JugadorRepositorio;
import com.campeonato.app.servicios.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
public class JugadorRestController {

    @Autowired
    private JugadorRepositorio jugadorRepo;

    @Autowired
    private SequenceGeneratorService sequenceService;

    @GetMapping
    public List<Jugador> listar() {
        return jugadorRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerPorId(@PathVariable Long id) {
        return jugadorRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jugador> crear(@RequestBody Jugador jugador) {
        if (jugador.getId() == null) {
            jugador.setId(sequenceService.generateSequence(Jugador.SEQUENCE_NAME));
        }
        return new ResponseEntity<>(jugadorRepo.save(jugador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizar(@PathVariable Long id, @RequestBody Jugador jugador) {
        if (!jugadorRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jugador.setId(id);
        return ResponseEntity.ok(jugadorRepo.save(jugador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!jugadorRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jugadorRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}