package tn.iit.dto;

import lombok.*;

@Data
@Builder
public class ClientDto {
    private Long cin;
    private String firstName;
    private String lastName;
}
