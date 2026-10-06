package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Jugador;
import com.campeonato.app.repositorios.JugadorRepositorio;
import com.campeonato.app.servicios.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
public class JugadorWebController {

    @Autowired
    private JugadorRepositorio jugadorRepo;

    @Autowired
    private SequenceGeneratorService sequenceService;

    // Página 2: Registros (Listado)
    @GetMapping({"", "/", "/listar"})
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorRepo.findAll());
        return "jugador/listar";
    }

    // Página 1: Formulario (Creación)
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("titulo", "Registrar Nuevo Jugador");
        return "jugador/formulario";
    }

    // Guardar
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("jugador") Jugador jugador) {
        if (jugador.getId() == null) {
            jugador.setId(sequenceService.generateSequence(Jugador.SEQUENCE_NAME));
        }
        jugadorRepo.save(jugador);
        return "redirect:/jugadores/listar";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable("id") Long id, Model model) {
        Jugador jugador = jugadorRepo.findById(id).orElse(null);
        if (jugador == null) return "redirect:/jugadores/listar";

        model.addAttribute("jugador", jugador);
        model.addAttribute("titulo", "Editar Jugador");
        return "jugador/formulario";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        jugadorRepo.deleteById(id);
        return "redirect:/jugadores/listar";
    }
}