package com.campeonato.app.controladores;

import com.campeonato.app.entidades.Asociacion;
import com.campeonato.app.repositorios.AsociacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionWebController {

    @Autowired
    private AsociacionRepositorio asociacionRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        return "asociacion/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Asociacion asociacion) {
        asociacionRepo.save(asociacion);
        return "redirect:/asociaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Asociacion asociacion = asociacionRepo.findById(id).orElse(null);
        if (asociacion == null) {
            return "redirect:/asociaciones";
        }
        model.addAttribute("asociacion", asociacion);
        return "asociacion/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        asociacionRepo.deleteById(id);
        return "redirect:/asociaciones";
    }
}