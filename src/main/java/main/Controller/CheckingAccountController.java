package main.Controller;

import main.DTO.CheckingAccountDTO;
import main.Service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckingAccountController {
    @Autowired
    private CheckingAccountService checkingAccountService;

    @GetMapping("/findAllCheckingAccount")
    public List<CheckingAccountDTO> findAll() {
        return checkingAccountService.findAll();
    }
}
