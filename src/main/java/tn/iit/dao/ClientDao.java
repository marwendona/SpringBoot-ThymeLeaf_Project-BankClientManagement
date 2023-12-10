package tn.iit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.entity.Client;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<Client, Long> {
    List<Client> findByFirstNameAndLastName(String firstName, String lastName);
}
