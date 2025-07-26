package com.mudaya.mudaya.presentation.views;

import com.mudaya.mudaya.domain.entities.Move;
import com.mudaya.mudaya.domain.entities.MoveAssignment;
import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.enums.MovingState;
import com.mudaya.mudaya.domain.managers.MoveManager;
import com.mudaya.mudaya.domain.managers.CotizationManager;
import com.mudaya.mudaya.domain.managers.TransporterManager;
import com.mudaya.mudaya.domain.managers.VehicleManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/movings")
public class MoveViewController {

    private final MoveManager moveManager;
    private final CotizationManager cotManager;
    private final VehicleManager vehicleManager;
    private final TransporterManager transporterManager;

    public MoveViewController(MoveManager moveManager,
                              CotizationManager cotManager,
                              VehicleManager vehicleManager,
                              TransporterManager transporterManager) {
        this.moveManager         = moveManager;
        this.cotManager          = cotManager;
        this.vehicleManager      = vehicleManager;
        this.transporterManager  = transporterManager;
    }

    /** Listado con filtros */
    @GetMapping
    public String listMoves(Model model,
                            @RequestParam(required = false) String filter,
                            @RequestParam(required = false) LocalDate date,
                            @RequestParam(required = false) MovingState state,
                            @RequestParam(defaultValue = "25") Integer limit) {
        try {
            List<Move> moves = moveManager.getAll(filter, date, state, limit);
            model.addAttribute("movings", moves);
            model.addAttribute("filter", filter);
            model.addAttribute("date", date);
            model.addAttribute("state", state);
        } catch (RuntimeException e) {
            model.addAttribute("movingsError", e.getMessage());
        }
        model.addAttribute("states", MovingState.values());
        return "movings";
    }

    /** Formulario de alta */
    @GetMapping("/new")
    public String newMoveForm(Model model, HttpSession session) {
        Move m = new Move();
        m.setTransporters(new ArrayList<>());

        m.setUser((User) session.getAttribute("currentUser")); // Asignar el user
        model.addAttribute("move", m);
        model.addAttribute("cotizations", cotManager.getAll(null, null));
        model.addAttribute("vehicles", vehicleManager.getAll());
        model.addAttribute("transporters", transporterManager.getAll());
        model.addAttribute("states", MovingState.values());
        return "moving-form";
    }

    /** Crear */
    @PostMapping
    public String create(@ModelAttribute Move move,
                         RedirectAttributes ra) {
        try {
            moveManager.create(move);
            ra.addFlashAttribute("successMessage", "Mudanza creada con éxito!");
            return "redirect:/movings";
        } catch (RuntimeException e) {
            ra.addFlashAttribute("movingsError", e.getMessage());
            return "redirect:/movings";
        }
    }

    /** Formulario de edición */
    @GetMapping("/edit/{id}")
    public String editMoveForm(@PathVariable UUID id, Model model) {
        try {
            Move m = moveManager.getOne(id);
            model.addAttribute("move", m);
            model.addAttribute("cotizations", cotManager.getAll(null, null));
            model.addAttribute("vehicles", vehicleManager.getAll());
            model.addAttribute("transporters", transporterManager.getAll());
            model.addAttribute("states", MovingState.values());
        } catch (RuntimeException e) {
            model.addAttribute("movingsError", e.getMessage());
            return "redirect:/movings";
        }
        return "moving-form";
    }

    /** Actualizar */
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute Move move,
                         RedirectAttributes ra) {
        try {
            moveManager.update(move);
            ra.addFlashAttribute("successMessage", "Mudanza actualizada con éxito!");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("movingsError", e.getMessage());
        }
        return "redirect:/movings";
    }

    /** Eliminar */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id,
                         RedirectAttributes ra) {
        try {
            moveManager.delete(id);
            ra.addFlashAttribute("successMessage", "Mudanza eliminada con éxito!");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("movingsError", e.getMessage());
        }
        return "redirect:/movings";
    }
}
