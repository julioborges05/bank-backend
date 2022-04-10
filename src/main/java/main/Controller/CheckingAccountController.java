package main.Controller;

import main.DTO.AgencyDTO;
import main.DTO.CheckingAccountDTO;
import main.Service.AgencyService;
import main.Service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkingAccount")
public class CheckingAccountController {
    @Autowired
    private CheckingAccountService checkingAccountService;

    @GetMapping("/findAll")
    public List<CheckingAccountDTO> findAll() {
        return checkingAccountService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<CheckingAccountDTO> createCheckingAccount(
            @RequestBody CheckingAccountDTO checkingAccountRequest) {

        CheckingAccountDTO checkingAccountDTO;

        try {
            checkingAccountDTO = checkingAccountService.createCheckingAccount(checkingAccountRequest);

            if(ObjectUtils.isEmpty(checkingAccountDTO.getAgencyDTO()))
                return new ResponseEntity<>(checkingAccountDTO, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(checkingAccountDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CheckingAccountDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
