package main.Controller;

import main.DTO.AccountHolderDTO;
import main.Service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountHolderController {
    @Autowired
    private AccountHolderService accountHolderService;

    @GetMapping("/findAllAccountHolders")
    public List<AccountHolderDTO> findAll() {
        return accountHolderService.findAll();
    }
}
