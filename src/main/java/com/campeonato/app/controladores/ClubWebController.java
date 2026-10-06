package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Club;
import com.campeonato.app.repositorios.*;
import com.campeonato.app.servicios.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clubes")
public class ClubWebController {

    @Autowired private ClubRepositorio clubRepo;
    @Autowired private EntrenadorRepositorio entrenadorRepo;
    @Autowired private JugadorRepositorio jugadorRepo;
    @Autowired private AsociacionRepositorio asociacionRepo;
    @Autowired private CompeticionRepositorio competicionRepo;
    @Autowired private SequenceGeneratorService sequenceService;

    // Página 2: Registros
    @GetMapping({"", "/", "/listar"})
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepo.findAll());
        return "club/listar";
    }

    // Página 1: Formulario
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("todosLosJugadores", jugadorRepo.findAll());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("todasLasCompeticiones", competicionRepo.findAll());
        model.addAttribute("titulo", "Registrar Club");
        return "club/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("club") Club club) {
        if (club.getId() == null) {
            club.setId(sequenceService.generateSequence(Club.SEQUENCE_NAME));
        }
        clubRepo.save(club);
        return "redirect:/clubes/listar";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable("id") Long id, Model model) {
        Club club = clubRepo.findById(id).orElse(null);
        if (club == null) return "redirect:/clubes/listar";

        model.addAttribute("club", club);
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("todosLosJugadores", jugadorRepo.findAll());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("todasLasCompeticiones", competicionRepo.findAll());
        model.addAttribute("titulo", "Editar Club");
        return "club/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        clubRepo.deleteById(id);
        return "redirect:/clubes/listar";
    }
}