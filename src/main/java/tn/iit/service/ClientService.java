package tn.iit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.dao.ClientDao;
import tn.iit.dto.ClientDto;
import tn.iit.entity.Client;
import tn.iit.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ClientService {
    private ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Long createClient(ClientDto clientDto) {
        var client = new Client();
        client.setCin(clientDto.getCin());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client = clientDao.save(client);

        return client.getCin();
    }

    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    public Client getClientByCin(Long cin) {
        return clientDao.findById(cin).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    public Long updateClient(Long cin, ClientDto updatedClientDto) {
        Client existingClient = clientDao.findById(cin).orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        existingClient.setFirstName(updatedClientDto.getFirstName());
        existingClient.setLastName(updatedClientDto.getLastName());
        clientDao.save(existingClient);
        return existingClient.getCin();
    }

    public void deleteClient(Long cin) {
        clientDao.deleteById(cin);
    }
}
