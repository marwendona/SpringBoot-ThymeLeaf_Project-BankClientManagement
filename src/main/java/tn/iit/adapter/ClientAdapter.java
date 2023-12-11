package tn.iit.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.iit.dto.ClientDto;
import tn.iit.entity.Client;

import java.util.stream.Collectors;

@Component
public class ClientAdapter {
    private AccountAdapter accountAdapter;

    @Autowired
    public ClientAdapter(AccountAdapter accountAdapter) {
        this.accountAdapter = accountAdapter;
    }

    public ClientDto convertToDto(Client client) {
        return ClientDto.builder()
                .cin(client.getCin())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .accountDtos(client.getAccounts().stream().map(accountAdapter::convertToDto).collect(Collectors.toList()))
                .build();
    }
}
