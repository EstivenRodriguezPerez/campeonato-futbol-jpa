package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Competicion;
import com.campeonato.app.repositorios.CompeticionRepositorio;
import com.campeonato.app.servicios.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competiciones")
public class CompeticionWebController {

    @Autowired
    private CompeticionRepositorio competicionRepo;

    @Autowired
    private SequenceGeneratorService sequenceService;

    // Página 2: Registros
    @GetMapping({"", "/", "/listar"})
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "competicion/listar";
    }

    // Página 1: Formulario
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        model.addAttribute("titulo", "Registrar Nueva Competición");
        return "competicion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("competicion") Competicion competicion) {
        if (competicion.getId() == null) {
            competicion.setId(sequenceService.generateSequence(Competicion.SEQUENCE_NAME));
        }
        competicionRepo.save(competicion);
        return "redirect:/competiciones/listar";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable("id") Long id, Model model) {
        Competicion comp = competicionRepo.findById(id).orElse(null);
        if (comp == null) return "redirect:/competiciones/listar";

        model.addAttribute("competicion", comp);
        model.addAttribute("titulo", "Editar Competición");
        return "competicion/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        competicionRepo.deleteById(id);
        return "redirect:/competiciones/listar";
    }
}