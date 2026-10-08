package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Competicion;
import com.campeonato.app.repositorios.CompeticionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competiciones")
public class CompeticionWebController {

    @Autowired
    private CompeticionRepositorio competicionRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "competicion/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competicion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Competicion competicion) {
        competicionRepo.save(competicion);
        return "redirect:/competiciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Competicion competicion = competicionRepo.findById(id).orElse(null);
        if (competicion == null) {
            return "redirect:/competiciones";
        }
        model.addAttribute("competicion", competicion);
        return "competicion/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        competicionRepo.deleteById(id);
        return "redirect:/competiciones";
    }
}