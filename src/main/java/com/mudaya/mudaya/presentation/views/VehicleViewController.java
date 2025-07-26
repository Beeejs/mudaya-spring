package com.mudaya.mudaya.presentation.views;

import com.mudaya.mudaya.domain.entities.Vehicle;
import com.mudaya.mudaya.domain.managers.VehicleManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/vehicles")
public class VehicleViewController {

    private final VehicleManager vehicleManager;

    public VehicleViewController(VehicleManager vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

    /** Listar vehículos */
    @GetMapping
    public String listVehicles(Model model) {
        try {
            List<Vehicle> vehicles = vehicleManager.getAll();
            model.addAttribute("vehicles", vehicles);
        } catch (RuntimeException e) {
            model.addAttribute("vehiclesError", e.getMessage());
        }
        return "vehicles";
    }

    /** Mostrar formulario de nuevo vehículo */
    @GetMapping("/new")
    public String newVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle-form";
    }

    /** Procesar creación */
    @PostMapping
    public String addVehicle(@ModelAttribute("vehicle") Vehicle vehicle,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        try {
            vehicleManager.create(vehicle);
            redirectAttributes.addFlashAttribute("successMessage", "Vehículo creado con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("vehicle", vehicle);
            return "vehicle-form";
        }
        return "redirect:/vehicles";
    }

    /** Mostrar formulario de edición */
    @GetMapping("/edit/{id}")
    public String editVehicleForm(@PathVariable UUID id, Model model) {
        try {
            Vehicle v = vehicleManager.getOne(id);
            model.addAttribute("vehicle", v);
        } catch (RuntimeException e) {
            model.addAttribute("vehiclesError", e.getMessage());
            return "redirect:/vehicles";
        }
        return "vehicle-form";
    }

    /** Procesar edición */
    @PostMapping("/edit/{id}")
    public String updateVehicle(@ModelAttribute("vehicle") Vehicle vehicle,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            vehicleManager.update(vehicle);
            redirectAttributes.addFlashAttribute("successMessage", "Vehículo actualizado con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("vehicle", vehicle);
            return "vehicle-form";
        }
        return "redirect:/vehicles";
    }

    /** Eliminar vehículo */
    @PostMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable UUID id,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            vehicleManager.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Vehículo eliminado con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("vehiclesError", e.getMessage());
            return "redirect:/vehicles";
        }
        return "redirect:/vehicles";
    }
}
