package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Asociacion;
import com.campeonato.app.repositorios.AsociacionRepositorio;
import com.campeonato.app.servicios.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionWebController {

    @Autowired
    private AsociacionRepositorio asociacionRepo;

    @Autowired
    private SequenceGeneratorService sequenceService;

    // Página 2: Registros
    @GetMapping({"", "/", "/listar"})
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        return "asociacion/listar";
    }

    // Página 1: Formulario
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        model.addAttribute("titulo", "Registrar Nueva Asociación");
        return "asociacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("asociacion") Asociacion asociacion) {
        if (asociacion.getId() == null) {
            asociacion.setId(sequenceService.generateSequence(Asociacion.SEQUENCE_NAME));
        }
        asociacionRepo.save(asociacion);
        return "redirect:/asociaciones/listar";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable("id") Long id, Model model) {
        Asociacion asociacion = asociacionRepo.findById(id).orElse(null);
        if (asociacion == null) return "redirect:/asociaciones/listar";

        model.addAttribute("asociacion", asociacion);
        model.addAttribute("titulo", "Editar Asociación");
        return "asociacion/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        asociacionRepo.deleteById(id);
        return "redirect:/asociaciones/listar";
    }
}