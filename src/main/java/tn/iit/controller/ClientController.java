package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tn.iit.dto.ClientDto;
import tn.iit.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ModelAndView getAllClients() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clients", clientService.getAllClients());
        modelAndView.setViewName("clients");
        return modelAndView;
    }

    @PostMapping
    public String createClient(@RequestParam(name = "cin") Long cin,
                               @RequestParam(name = "firstName") String firstName,
                               @RequestParam(name = "lastName") String lastName,
                               @RequestParam(name = "address") String address) {
        ClientDto clientDto = ClientDto.builder()
                .cin(cin)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .build();
        clientService.createClient(clientDto);
        return "redirect:/clients";
    }

    @GetMapping("/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new ClientDto());
        return "add-client";
    }

    @GetMapping("/delete/{cin}")
    public String deleteClient(@PathVariable(name = "cin") Long cin) {
        clientService.deleteClient(cin);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{cin}")
    public String editClient(@PathVariable(name = "cin") Long cin, Model model) {
        ClientDto clientDto = clientService.getClientByCin(cin);
        model.addAttribute("client", clientDto);
        return "edit-client";
    }

    @PostMapping("/{cin}")
    public String updateClient(@PathVariable(name = "cin") Long cin, @ModelAttribute ClientDto updatedClientDto) {
        clientService.updateClient(cin, updatedClientDto);
        return "redirect:/clients";
    }

    @PostMapping("/search")
    public ModelAndView searchClients(@RequestParam(name = "firstName") String firstName,
                                      @RequestParam(name = "lastName") String lastName) {
        ModelAndView modelAndView = new ModelAndView();
        if (!"".equals(firstName) && !"".equals(lastName)) {
            modelAndView.addObject("clients", clientService.getClientsByFirstNameAndLastName(firstName, lastName));
        } else {
            modelAndView.addObject("clients", clientService.getAllClients());
        }
        modelAndView.setViewName("clients");
        return modelAndView;
    }
}
