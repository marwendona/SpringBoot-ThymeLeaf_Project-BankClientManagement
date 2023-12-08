package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.dto.ClientDto;
import tn.iit.entity.Client;
import tn.iit.service.ClientService;

import java.util.List;

import static tn.iit.application.ApplicationProperties.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/clients")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Long> createClient(@RequestBody ClientDto clientDto) {
        Long newCin = clientService.createClient(clientDto);
        return ResponseEntity.ok(newCin);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{cin}")
    public ResponseEntity<Client> getClientByCin(@PathVariable Long cin) {
        Client client = clientService.getClientByCin(cin);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cin}")
    public ResponseEntity<Long> updateClient(@PathVariable Long cin, @RequestBody ClientDto updatedClientDto) {
        Long updatedCin = clientService.updateClient(cin, updatedClientDto);
        if (updatedCin != null) {
            return ResponseEntity.ok(updatedCin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cin}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long cin) {
        clientService.deleteClient(cin);
        return ResponseEntity.noContent().build();
    }
}
