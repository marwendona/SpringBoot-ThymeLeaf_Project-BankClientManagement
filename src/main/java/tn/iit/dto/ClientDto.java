package tn.iit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private Long cin;
    private String firstName;
    private String lastName;
    private String address;

    public ClientDto(Long cin, String firstName, String lastName, String address) {
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public ClientDto() {
    }
}
