package com.mudaya.mudaya.presentation.views;

import com.mudaya.mudaya.domain.entities.Client;
import com.mudaya.mudaya.domain.entities.Cotization;
import com.mudaya.mudaya.domain.managers.ClientManager;
import com.mudaya.mudaya.domain.managers.CotizationManager;
import com.mudaya.mudaya.domain.managers.VehicleManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cotizations")
public class CotizationViewController {

    private final CotizationManager cotManager;
    private final VehicleManager vehicleManager;
    private final ClientManager clientManager;

    public CotizationViewController(CotizationManager cotManager, VehicleManager vehicleManager,  ClientManager clientManager) {
        this.cotManager = cotManager;
        this.vehicleManager = vehicleManager;
        this.clientManager = clientManager;
    }

    /** Listado de cotizaciones de un cliente */
    @GetMapping("/client/{clientId}")
    public String listByClient(@PathVariable UUID clientId, Model model) {
        try {
            List<Cotization> cotizations = cotManager.getAll(clientId.toString(), null);
            model.addAttribute("cotizations", cotizations);
            // Aca obtenemos el cliente y pasamos su nombre
            Client cliente = clientManager.getOne(clientId);
            model.addAttribute("clientName", cliente.getName());
            model.addAttribute("clientId", clientId);
        } catch (RuntimeException e) {
            model.addAttribute("cotizationsError", e.getMessage());
        }
        return "cotizations";
    }

    /** Formulario de nueva cotización para un cliente */
    @GetMapping("/new/{clientId}")
    public String newForm(@PathVariable UUID clientId, Model model) {
        try {
            Cotization c = new Cotization();
            // fijar sólo la referencia al cliente
            c.setClient(new com.mudaya.mudaya.domain.entities.Client(clientId, null, null, null, null, null));
            model.addAttribute("cotization", c);
            model.addAttribute("vehiclesList", vehicleManager.getAll());
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
        }
        return "cotization-form";
    }

    /** Formulario de edición de una cotización */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {
        try {
            Cotization c = cotManager.getOne(id);
            model.addAttribute("cotization", c);
            model.addAttribute("vehiclesList", vehicleManager.getAll());
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
        }
        return "cotization-form";
    }

    /** Crear cotización */
    @PostMapping
    public String create(@ModelAttribute Cotization cotization,
                         Model model,
                         RedirectAttributes ra) {
        try {
            Cotization created = cotManager.create(cotization);
            ra.addFlashAttribute("successMessage", "Cotización creada con éxito!");
            // redirige al listado de este cliente
            return "redirect:/cotizations/client/" + created.getClient().getId();
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("cotization", cotization);
            model.addAttribute("vehiclesList", vehicleManager.getAll());
            return "cotization-form";
        }
    }

    /** Actualizar cotización */
    @PostMapping("/edit/{id}/{idClient}")
    public String update(@ModelAttribute Cotization cotization,
                         @PathVariable UUID id,
                         @PathVariable UUID idClient,
                         Model model,
                         RedirectAttributes ra) {
        try {
            cotManager.update(cotization);

            ra.addFlashAttribute("successMessage", "Cotización actualizada con éxito!");
            return "redirect:/cotizations/client/" + idClient;
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("cotization", cotization);
            model.addAttribute("vehiclesList", vehicleManager.getAll());
            return "cotization-form";
        }
    }

    /** Eliminar cotización */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id,
                         RedirectAttributes ra,
                         Model model) {
        UUID clientId = null;
        try {
            Cotization toDelete = cotManager.getOne(id);
            clientId = toDelete.getClient().getId();
            cotManager.delete(id);
            ra.addFlashAttribute("successMessage", "Cotización eliminada con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("cotizationsError", e.getMessage());
        }
        // si pudimos obtener el clientId, redirigimos al listado; si no, al listado general
        return "redirect:" + (clientId != null
                ? "/cotizations/client/" + clientId
                : "/clients");
    }
}
