package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Club;
import com.campeonato.app.repositorios.ClubRepositorio;
import com.campeonato.app.repositorios.EntrenadorRepositorio;
import com.campeonato.app.repositorios.AsociacionRepositorio;
import com.campeonato.app.repositorios.CompeticionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clubes")
public class ClubWebController {

    @Autowired
    private ClubRepositorio clubRepo;

    @Autowired
    private EntrenadorRepositorio entrenadorRepo;

    @Autowired
    private AsociacionRepositorio asociacionRepo;

    @Autowired
    private CompeticionRepositorio competicionRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepo.findAll());
        return "club/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "club/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Club club) {
        clubRepo.save(club);
        return "redirect:/clubes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Club club = clubRepo.findById(id).orElse(null);
        if (club == null) {
            return "redirect:/clubes";
        }
        model.addAttribute("club", club);
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "club/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clubRepo.deleteById(id);
        return "redirect:/clubes";
    }
}