package tn.iit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tn.iit.dto.AccountDto;
import tn.iit.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ModelAndView getAllAccounts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountService.getAllAccounts());
        modelAndView.setViewName("accounts");
        return modelAndView;
    }

    @PostMapping
    public String createAccount(@RequestParam(name = "rib") Long rib,
                                @RequestParam(name = "balance") float balance,
                                @RequestParam(name = "cin") Long cin) {
        AccountDto accountDto = AccountDto.builder()
                .rib(rib)
                .balance(balance)
                .build();
        accountService.createAccount(accountDto, cin);
        return "redirect:/accounts";
    }

    @GetMapping("/add")
    public String showAddAccountForm(Model model) {
        model.addAttribute("account", new AccountDto());
        return "add-account";
    }

    @GetMapping("/delete/{rib}")
    public String deleteAccount(@PathVariable(name = "rib") Long rib) {
        accountService.deleteAccount(rib);
        return "redirect:/accounts";
    }

    @GetMapping("/edit/{rib}")
    public String editAccount(@PathVariable Long rib, Model model) {
        AccountDto accountDto = accountService.getAccountByRib(rib);
        model.addAttribute("account", accountDto);
        return "edit-account";
    }

    @PostMapping("/{rib}")
    public String updateAccount(@PathVariable(name = "rib") Long rib, @ModelAttribute AccountDto updatedAccountDto) {
        accountService.updateAccount(rib, updatedAccountDto);
        return "redirect:/accounts";
    }
}
