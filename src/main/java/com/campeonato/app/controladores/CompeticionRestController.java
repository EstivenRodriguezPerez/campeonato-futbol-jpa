package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Competicion;
import com.campeonato.app.repositorios.CompeticionRepositorio;
import com.campeonato.app.servicios.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competiciones")
@CrossOrigin(origins = "*")
public class CompeticionRestController {

    @Autowired
    private CompeticionRepositorio competicionRepo;

    @Autowired
    private SequenceGeneratorService sequenceService;

    @GetMapping
    public List<Competicion> listar() {
        return competicionRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competicion> obtenerPorId(@PathVariable Long id) {
        return competicionRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Competicion> crear(@RequestBody Competicion competicion) {
        if (competicion.getId() == null) {
            competicion.setId(sequenceService.generateSequence(Competicion.SEQUENCE_NAME));
        }
        return new ResponseEntity<>(competicionRepo.save(competicion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Competicion> actualizar(@PathVariable Long id, @RequestBody Competicion competicion) {
        if (!competicionRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        competicion.setId(id);
        return ResponseEntity.ok(competicionRepo.save(competicion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!competicionRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        competicionRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}