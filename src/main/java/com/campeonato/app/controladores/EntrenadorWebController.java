package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Entrenador;
import com.campeonato.app.repositorios.EntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorWebController {

    @Autowired
    private EntrenadorRepositorio entrenadorRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        return "entrenador/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador) {
        entrenadorRepo.save(entrenador);
        return "redirect:/entrenadores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Entrenador entrenador = entrenadorRepo.findById(id).orElse(null);
        if (entrenador == null) {
            return "redirect:/entrenadores";
        }
        model.addAttribute("entrenador", entrenador);
        return "entrenador/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        entrenadorRepo.deleteById(id);
        return "redirect:/entrenadores";
    }
}