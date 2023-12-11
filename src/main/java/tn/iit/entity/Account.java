package tn.iit.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "account")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rib")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rib")
    private Long rib;

    @Column(name = "balance")
    private float balance;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @ToString.Exclude
    private Client client;
}
