package tn.iit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class ClientDto {
    private String cin;
    private String firstName;
    private String lastName;
}
