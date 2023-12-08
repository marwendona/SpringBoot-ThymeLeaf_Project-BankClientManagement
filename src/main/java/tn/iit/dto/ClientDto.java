package tn.iit.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientDto {
    private String cin;
    private String firstName;
    private String lastName;
}
