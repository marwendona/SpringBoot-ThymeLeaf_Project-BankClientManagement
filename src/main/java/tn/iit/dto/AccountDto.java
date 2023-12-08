package tn.iit.dto;

import lombok.*;

@Data
@Builder
public class AccountDto {
    private Long rib;
    private float balance;
}
