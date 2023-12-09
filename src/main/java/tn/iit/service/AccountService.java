package tn.iit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    public AccountService(AccountDao accountDao, ClientDao clientDao) {
        this.accountDao = accountDao;
        this.clientDao = clientDao;
    }

    public Long createAccount(AccountDto accountDto, Long cin) {
        Client client = clientDao.findById(cin).orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        Account account = new Account();
        account.setBalance(accountDto.getBalance());
        account.setClient(client);

        account = accountDao.save(account);
        return account.getRib();
    }

    public Long updateAccount(Long rib, AccountDto updatedAccountDto) {
        Account existingAccount = accountDao.findById(rib)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        existingAccount.setBalance(updatedAccountDto.getBalance());
        accountDao.save(existingAccount);
        return existingAccount.getRib();
    }

    public void deleteAccount(Long rib) {
        accountDao.deleteById(rib);
    }

    public Account getAccountByRib(Long rib) {
        return accountDao.findById(rib).orElse(null);
    }

    public List<Account> getAllAccounts() {
        return accountDao.findAll();
    }
}
