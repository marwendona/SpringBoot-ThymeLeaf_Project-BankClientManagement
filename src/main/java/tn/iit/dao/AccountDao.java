package tn.iit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
}
