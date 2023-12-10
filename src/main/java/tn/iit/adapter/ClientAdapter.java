package tn.iit.adapter;

import org.springframework.stereotype.Component;
import tn.iit.dto.ClientDto;
import tn.iit.entity.Client;

@Component
public class ClientAdapter {
    public ClientDto convertToDto(Client client) {
        return ClientDto.builder()
                .cin(client.getCin())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .build();
    }
}
