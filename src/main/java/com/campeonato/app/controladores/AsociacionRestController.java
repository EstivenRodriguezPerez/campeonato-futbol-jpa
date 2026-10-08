package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Asociacion;
import com.campeonato.app.repositorios.AsociacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asociaciones")
@CrossOrigin(origins = "*")
public class AsociacionRestController {

    @Autowired
    private AsociacionRepositorio asociacionRepo;

    @GetMapping
    public List<Asociacion> listar() {
        return asociacionRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asociacion> obtenerPorId(@PathVariable Long id) {
        return asociacionRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asociacion> crear(@RequestBody Asociacion asociacion) {
        return new ResponseEntity<>(asociacionRepo.save(asociacion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asociacion> actualizar(@PathVariable Long id, @RequestBody Asociacion asociacion) {
        if (!asociacionRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        asociacion.setId(id);
        return ResponseEntity.ok(asociacionRepo.save(asociacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!asociacionRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        asociacionRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}