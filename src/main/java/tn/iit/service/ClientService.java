package tn.iit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.iit.adapter.ClientAdapter;
import tn.iit.dao.ClientDao;
import tn.iit.dto.ClientDto;
import tn.iit.entity.Client;
import tn.iit.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientDao clientDao;
    private final ClientAdapter clientAdapter;

    @Autowired
    public ClientService(ClientDao clientDao, ClientAdapter clientAdapter) {
        this.clientDao = clientDao;
        this.clientAdapter = clientAdapter;
    }

    public Long createClient(ClientDto clientDto) {
        var client = new Client();
        client.setCin(clientDto.getCin());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setAddress(clientDto.getAddress());
        client = clientDao.save(client);

        return client.getCin();
    }

    public List<Client> getAllClients() {
        return clientDao.findAll(Sort.by(Sort.Direction.ASC, "cin"));
    }

    public ClientDto getClientByCin(Long cin) {
        Client client = clientDao.findById(cin).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return clientAdapter.convertToDto(client);
    }

    public Long updateClient(Long cin, ClientDto updatedClientDto) {
        Client existingClient = clientDao.findById(cin).orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        existingClient.setFirstName(updatedClientDto.getFirstName());
        existingClient.setLastName(updatedClientDto.getLastName());
        existingClient.setAddress(updatedClientDto.getAddress());
        clientDao.save(existingClient);
        return existingClient.getCin();
    }

    public void deleteClient(Long cin) {
        clientDao.deleteById(cin);
    }

    public List<ClientDto> getClientsByFirstNameAndLastName(String firstName, String lastName) {
        List<Client> clients;

        if (!"".equals(firstName) && !"".equals(lastName)) {
            clients = clientDao.findByFirstNameAndLastName(firstName, lastName);
        } else if (!"".equals(firstName)) {
            clients = clientDao.findByFirstName(firstName);
        } else if (!"".equals(lastName)) {
            clients = clientDao.findByLastName(lastName);
        } else {
            clients = clientDao.findAll();
        }

        List<ClientDto> clientDtos = clients.stream()
                .map(clientAdapter::convertToDto)
                .collect(Collectors.toList());

        return clientDtos;
    }
}
