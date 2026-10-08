package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Jugador;
import com.campeonato.app.repositorios.JugadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
public class JugadorWebController {

    @Autowired
    private JugadorRepositorio jugadorRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorRepo.findAll());
        return "jugador/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugador/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Jugador jugador) {
        jugadorRepo.save(jugador);
        return "redirect:/jugadores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepo.findById(id).orElse(null);
        if (jugador == null) {
            return "redirect:/jugadores";
        }
        model.addAttribute("jugador", jugador);
        return "jugador/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        jugadorRepo.deleteById(id);
        return "redirect:/jugadores";
    }
}