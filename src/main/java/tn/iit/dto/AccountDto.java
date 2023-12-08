package tn.iit.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDto {
    private Integer rib;
    private float balance;
}
