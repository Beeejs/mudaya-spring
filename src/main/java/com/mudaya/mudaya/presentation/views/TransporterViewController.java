package com.mudaya.mudaya.presentation.views;

import com.mudaya.mudaya.domain.entities.Transporter;
import com.mudaya.mudaya.domain.managers.TransporterManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/transporters")
public class TransporterViewController {

    private final TransporterManager transporterManager;

    public TransporterViewController(TransporterManager transporterManager) {
        this.transporterManager = transporterManager;
    }

    /** Listado */
    @GetMapping
    public String listTransporters(Model model) {
        try {
            List<Transporter> transporters = transporterManager.getAll();
            model.addAttribute("transporters", transporters);
        } catch (RuntimeException e) {
            model.addAttribute("transportersError", e.getMessage());
        }
        return "transporters";
    }

    /** Formulario nuevo */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("transporter", new Transporter());
        return "transporter-form";
    }

    /** Crear */
    @PostMapping
    public String create(@ModelAttribute("transporter") Transporter transporter,
                         Model model,
                         RedirectAttributes ra) {
        try {
            transporterManager.create(transporter);
            ra.addFlashAttribute("successMessage", "Transportador creado con éxito!");
            return "redirect:/transporters";
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("transporter", transporter);
            return "transporter-form";
        }
    }

    /** Formulario edición */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {
        try {
            Transporter t = transporterManager.getOne(id);
            model.addAttribute("transporter", t);
        } catch (RuntimeException e) {
            model.addAttribute("transportersError", e.getMessage());
            return "redirect:/transporters";
        }
        return "transporter-form";
    }

    /** Actualizar */
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("transporter") Transporter transporter,
                         @PathVariable UUID id,
                         Model model,
                         RedirectAttributes ra) {
        try {
            transporter.setId(id);
            transporterManager.update(transporter);
            ra.addFlashAttribute("successMessage", "Transportador actualizado con éxito!");
            return "redirect:/transporters";
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("transporter", transporter);
            return "transporter-form";
        }
    }

    /** Eliminar */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id,
                         RedirectAttributes ra,
                         Model model) {
        try {
            transporterManager.delete(id);
            ra.addFlashAttribute("successMessage", "Transportador eliminado con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("transportersError", e.getMessage());
        }
        return "redirect:/transporters";
    }

}
