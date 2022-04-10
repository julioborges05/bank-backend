package main.Controller;

import main.DTO.CheckingAccountDTO;
import main.Service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            @RequestBody CheckingAccountDTO checkingAccountRequest
    ) {

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
            @RequestBody CheckingAccountDTO checkingAccountRequest
    ) {

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
            @PathVariable Double value, @RequestBody CheckingAccountDTO checkingAccountRequest
    ) {

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

    @PostMapping("/transference")
    public ResponseEntity<Double> makeTransference(
            @RequestParam(name = "originId", required = true) Integer originId,
            @RequestParam(name = "value", required = true) Double value,
            @RequestBody List<CheckingAccountDTO> checkingAccountListRequest
    ) {

        CheckingAccountDTO checkingAccountDTO;
        try {
            checkingAccountDTO = checkingAccountService.makeTransference(checkingAccountListRequest, value, originId);

            if (ObjectUtils.isEmpty(checkingAccountDTO) || ObjectUtils.isEmpty(checkingAccountDTO.getAgencyDTO().getId()))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            if(value <= 0 || checkingAccountDTO.getBalance() < checkingAccountDTO.getLimit() * (-1))
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

            return new ResponseEntity<>(checkingAccountDTO.getBalance(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Map<String, List<CheckingAccountDTO>>> findDataByCpf(
            @PathVariable String cpf
    ) {

        Map<String, List<CheckingAccountDTO>> informationMap;
        try {
            informationMap = checkingAccountService.findAllInformationByCpf(cpf);

            if (ObjectUtils.isEmpty(informationMap))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(informationMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/disableCheckingAccount")
    public ResponseEntity<CheckingAccountDTO> disableCheckingAccount(
            @RequestBody CheckingAccountDTO checkingAccountListRequest
    ) {
        CheckingAccountDTO checkingAccountDTO;
        try {
            checkingAccountDTO = checkingAccountService.disableCheckingAccount(checkingAccountListRequest.getAgencyDTO().getId(),
                    checkingAccountListRequest.getId());

            if (ObjectUtils.isEmpty(checkingAccountDTO) || ObjectUtils.isEmpty(checkingAccountDTO.getAgencyDTO().getId()))
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

            if(checkingAccountDTO.getIsActive().equals("Already disabled"))
                return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);

            return new ResponseEntity<>(checkingAccountDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
