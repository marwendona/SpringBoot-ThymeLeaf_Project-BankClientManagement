package tn.iit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    private Long rib;
    private float balance;

    public AccountDto(Long rib, float balance) {
        this.rib = rib;
        this.balance = balance;
    }

    public AccountDto() {
    }
}
