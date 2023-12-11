package tn.iit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.iit.adapter.AccountAdapter;
import tn.iit.dao.AccountDao;
import tn.iit.dao.ClientDao;
import tn.iit.dto.AccountDto;
import tn.iit.entity.Account;
import tn.iit.entity.Client;
import tn.iit.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class AccountService {
    private final AccountDao accountDao;
    private final ClientDao clientDao;
    private final AccountAdapter accountAdapter;

    @Autowired
    public AccountService(AccountDao accountDao, ClientDao clientDao, AccountAdapter accountAdapter) {
        this.accountDao = accountDao;
        this.clientDao = clientDao;
        this.accountAdapter = accountAdapter;
    }

    public Long createAccount(AccountDto accountDto, Long cin) {
        Client client = clientDao.findById(cin).orElseThrow(() -> new ResourceNotFoundException("Client with CIN " + cin.toString() + " is not found"));

        Account account = new Account();
        account.setBalance(accountDto.getBalance());
        account.setClient(client);

        account = accountDao.save(account);
        return account.getRib();
    }

    public Long updateAccount(Long rib, AccountDto updatedAccountDto) {
        Account existingAccount = accountDao.findById(rib)
                .orElseThrow(() -> new ResourceNotFoundException("Account with Rib " + rib.toString() + " is not found"));

        existingAccount.setBalance(updatedAccountDto.getBalance());
        accountDao.save(existingAccount);
        return existingAccount.getRib();
    }

    public void deleteAccount(Long rib) {
        accountDao.deleteById(rib);
    }

    public AccountDto getAccountByRib(Long rib) {
        Account account = accountDao.findById(rib).orElseThrow(() -> new ResourceNotFoundException("Account with Rib " + rib.toString() + " is not found"));
        return accountAdapter.convertToDto(account);
    }

    public List<Account> getAllAccounts() {
        return accountDao.findAll(Sort.by(Sort.Direction.DESC, "rib"));
    }
}
