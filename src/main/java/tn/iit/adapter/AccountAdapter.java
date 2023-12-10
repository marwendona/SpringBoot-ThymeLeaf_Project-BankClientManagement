package tn.iit.adapter;

import org.springframework.stereotype.Component;
import tn.iit.dto.AccountDto;
import tn.iit.entity.Account;

@Component
public class AccountAdapter {
    public AccountDto convertToDto(Account account) {
        return AccountDto.builder()
                .rib(account.getRib())
                .balance(account.getBalance())
                .build();
    }
}
