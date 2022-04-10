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

            if (ObjectUtils.isEmpty(checkingAccountDTO.getAgencyDTO()))
                return new ResponseEntity<>(checkingAccountDTO, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(checkingAccountDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CheckingAccountDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBalance")
    public ResponseEntity<Double> getBalance(
            @RequestBody CheckingAccountDTO checkingAccountRequest) {

        CheckingAccountDTO checkingAccountDTO;
        try {
            checkingAccountDTO = checkingAccountService.findByAgencyIdAndCheckingAccountId(checkingAccountRequest.getAgencyDTO().getId(),
                    checkingAccountRequest.getId());

            if (ObjectUtils.isEmpty(checkingAccountDTO) || ObjectUtils.isEmpty(checkingAccountDTO.getAgencyDTO().getId()))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(checkingAccountDTO.getBalance(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/withdrawal/{value}")
    public ResponseEntity<Double> makeWithdrawal(
            @PathVariable Double value, @RequestBody CheckingAccountDTO checkingAccountRequest) {

        CheckingAccountDTO checkingAccountDTO;
        try {
            checkingAccountDTO = checkingAccountService.makeWithdrawal(checkingAccountRequest.getAgencyDTO().getId(),
                    checkingAccountRequest.getId(), value);

            if(value <= 0 || checkingAccountDTO.getBalance() < checkingAccountDTO.getLimit() * (-1))
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

            if (ObjectUtils.isEmpty(checkingAccountDTO) || ObjectUtils.isEmpty(checkingAccountDTO.getAgencyDTO().getId()))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(checkingAccountDTO.getBalance(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
