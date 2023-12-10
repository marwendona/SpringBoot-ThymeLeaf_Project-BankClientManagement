package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.dto.AccountDto;
import tn.iit.entity.Account;
import tn.iit.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{cin}")
    public ResponseEntity<Long> createAccount(@PathVariable Long cin, @RequestBody AccountDto accountDto) {
        Long newRib = accountService.createAccount(accountDto, cin);
        return ResponseEntity.ok(newRib);
    }

    @GetMapping("/{rib}")
    public ResponseEntity<Account> getAccountByRib(@PathVariable Long rib) {
        Account account = accountService.getAccountByRib(rib);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{rib}")
    public ResponseEntity<Long> updateAccount(@PathVariable Long rib, @RequestBody AccountDto updatedAccountDto) {
        Long updatedRib = accountService.updateAccount(rib, updatedAccountDto);
        if (updatedRib != null) {
            return ResponseEntity.ok(updatedRib);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rib}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long rib) {
        accountService.deleteAccount(rib);
        return ResponseEntity.noContent().build();
    }
}
