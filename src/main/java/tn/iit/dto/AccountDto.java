package tn.iit.dto;

import lombok.Builder;
import lombok.Data;
import tn.iit.entity.Client;

@Data
@Builder
public class AccountDto {
    private Long rib;
    private float balance;
    private Client client;

    public AccountDto(Long rib, float balance, Client client) {
        this.rib = rib;
        this.balance = balance;
        this.client = client;
    }

    public AccountDto() {
    }
}
