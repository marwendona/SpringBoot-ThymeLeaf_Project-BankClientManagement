package tn.iit.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class ClientDto {
    private Long cin;
    private String firstName;
    private String lastName;
    private String address;
    private List<AccountDto> accountDtos;
}
