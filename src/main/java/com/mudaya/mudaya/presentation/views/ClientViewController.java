package com.mudaya.mudaya.presentation.views;

import com.mudaya.mudaya.domain.entities.Client;
import com.mudaya.mudaya.domain.managers.ClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientViewController {

    private final ClientManager clientManager;

    public ClientViewController(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @GetMapping
    public String listClients(Model model,
                              @RequestParam(defaultValue = "") String filter,
                              @RequestParam(defaultValue = "25") Integer limit) {
        try {
            List<Client> clients = clientManager.getAll(filter, limit);
            model.addAttribute("clients", clients);
            model.addAttribute("newClient", new Client());
        }
        catch (RuntimeException e) {
            model.addAttribute("clientsError", e.getMessage());
        }
        return "clients";

    }

    /** Mostrar formulario de nuevo cliente */
    @GetMapping("/new")
    public String newClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client-form";
    }

    /** Procesar creación */
    @PostMapping
    public String addClient(@ModelAttribute("client") Client client,
                            Model model, RedirectAttributes redirectAttributes) {
        try {
            clientManager.create(client);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente creado con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("client", client);
            return "client-form";
        }
        return "redirect:/clients";
    }

    /** Mostrar formulario de edición */
    @GetMapping("/edit/{id}")
    public String editClientForm(@PathVariable UUID id, Model model) {
        try {
            Client c = clientManager.getOne(id);
            model.addAttribute("client", c);
        } catch (RuntimeException e) {
            model.addAttribute("clientsError", e.getMessage());
            return "redirect:/clients";
        }
        return "client-form";
    }

    /** Procesar edición */
    @PostMapping("/edit/{id}")
    public String updateClient(@ModelAttribute("client") Client client,
                               Model model, RedirectAttributes redirectAttributes) {
        try {
            clientManager.update(client);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente actualizado con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("client", client);
            return "client-form";
        }
        return "redirect:/clients";
    }

    /** Eliminar cliente */
    @PostMapping("/delete/{id}")
    public String deleteClient(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        try {
            clientManager.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente eliminado con éxito!");
        } catch (RuntimeException e) {
            model.addAttribute("clientsError", e.getMessage());
            return "redirect:/clients";
        }
        return "redirect:/clients";
    }
}
