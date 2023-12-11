package tn.iit.dto;

import lombok.*;
import tn.iit.entity.Client;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class AccountDto {
    private Long rib;
    private float balance;
    private Client client;
}
