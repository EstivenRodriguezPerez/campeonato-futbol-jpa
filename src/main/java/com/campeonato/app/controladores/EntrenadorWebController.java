package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Entrenador;
import com.campeonato.app.repositorios.EntrenadorRepositorio;
import com.campeonato.app.servicios.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorWebController {

    @Autowired
    private EntrenadorRepositorio entrenadorRepo;

    @Autowired
    private SequenceGeneratorService sequenceService;

    // Página 2: Registros (Listado)
    @GetMapping({"", "/", "/listar"})
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        return "entrenador/listar";
    }

    // Página 1: Formulario (Creación)
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("titulo", "Registrar Nuevo Entrenador");
        return "entrenador/formulario";
    }

    // Guardar (creación o edición)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("entrenador") Entrenador entrenador) {
        if (entrenador.getId() == null) {
            entrenador.setId(sequenceService.generateSequence(Entrenador.SEQUENCE_NAME));
        }
        entrenadorRepo.save(entrenador);
        return "redirect:/entrenadores/listar";
    }

    // Cargar formulario para editar
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable("id") Long id, Model model) {
        Entrenador entrenador = entrenadorRepo.findById(id).orElse(null);
        if (entrenador == null) return "redirect:/entrenadores/listar";

        model.addAttribute("entrenador", entrenador);
        model.addAttribute("titulo", "Editar Entrenador");
        return "entrenador/formulario";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        entrenadorRepo.deleteById(id);
        return "redirect:/entrenadores/listar";
    }
}