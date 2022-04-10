package main.Service;

import main.DAO.CheckingAccountDAO;
import main.DTO.AgencyDTO;
import main.DTO.CheckingAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CheckingAccountService {
    @Autowired
    private CheckingAccountDAO checkingAccountDAO;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private AccountHolderService accountHolderService;

    public List<CheckingAccountDTO> findAll() {
        List<CheckingAccountDTO> accountDTOS = checkingAccountDAO.findAll();

        for(CheckingAccountDTO accountDTO : accountDTOS) {
            accountDTO.setAccountHolderDTO(accountHolderService.findById(accountDTO.getAccountHolderDTO().getId()));
            accountDTO.setAgencyDTO(agencyService.findById(accountDTO.getAgencyDTO().getId()));
        }

        return accountDTOS;
    }

    public CheckingAccountDTO createCheckingAccount(CheckingAccountDTO checkingAccountToBeCreated) {
        CheckingAccountDTO checkingAccountDTO = new CheckingAccountDTO();

        AgencyDTO agencyDTO = agencyService.findById(checkingAccountToBeCreated.getAgencyDTO().getId());
        if(ObjectUtils.isEmpty(agencyDTO))
            return new CheckingAccountDTO();
        else
            checkingAccountDTO.setAgencyDTO(agencyDTO);

        checkingAccountDTO.setAccountHolderDTO(accountHolderService
                .createAccountHolderIfNotExists(checkingAccountToBeCreated.getAccountHolderDTO()));
        checkingAccountDTO.setLimit(checkingAccountToBeCreated.getLimit());
        checkingAccountDTO.setBalance(checkingAccountToBeCreated.getBalance());
        checkingAccountDTO.setIsActive(checkingAccountToBeCreated.getIsActive());

        if(!checkingAccountAlreadyExists(checkingAccountDTO))
            checkingAccountDAO.createCheckingAccount(checkingAccountDTO);

        return checkingAccountDAO.findByCheckingAccountParameters(checkingAccountDTO);
    }

    private boolean checkingAccountAlreadyExists(CheckingAccountDTO checkingAccountDTO) {
        return !ObjectUtils.isEmpty(checkingAccountDAO.findByCheckingAccountParameters(checkingAccountDTO));
    }

    public CheckingAccountDTO findByAgencyIdAndCheckingAccountId(Integer agencyId, Integer checkingAccountId) {
        return checkingAccountDAO.findByAgencyIdAndCheckingAccountId(agencyId, checkingAccountId);
    }

    public CheckingAccountDTO makeWithdrawal(Integer agencyId, Integer checkingAccountId, Double withdrawalValue) {
        CheckingAccountDTO databaseCheckingAccount = checkingAccountDAO.findByAgencyIdAndCheckingAccountId(agencyId, checkingAccountId);

        if(ObjectUtils.isEmpty(databaseCheckingAccount))
            return null;

        if(databaseCheckingAccount.getBalance() - withdrawalValue < databaseCheckingAccount.getLimit() * (-1)) {
            databaseCheckingAccount.setBalance(databaseCheckingAccount.getBalance() - withdrawalValue);
            return databaseCheckingAccount;
        }

        checkingAccountDAO.updateExistentCheckingAccountBalanceByCheckingAccountIdAndAgencyId(checkingAccountId, agencyId, databaseCheckingAccount.getBalance() - withdrawalValue);

        return checkingAccountDAO.findByAgencyIdAndCheckingAccountId(agencyId, checkingAccountId);
    }

    public CheckingAccountDTO makeTransference(List<CheckingAccountDTO> accountList, Double value, Integer originId) {
        if(accountList.size() != 2 || accountList.get(0).getId().equals(accountList.get(1).getId())) return null;

        CheckingAccountDTO originAccount = null;
        CheckingAccountDTO receiverAccount = null;

        for(CheckingAccountDTO accountDTO : accountList) {
            if(accountDTO.getId().equals(originId))
                originAccount = checkingAccountDAO.findByAgencyIdAndCheckingAccountId(accountDTO.getAgencyDTO().getId(), accountDTO.getId());
            else
                receiverAccount = checkingAccountDAO.findByAgencyIdAndCheckingAccountId(accountDTO.getAgencyDTO().getId(), accountDTO.getId());
        }

        if(ObjectUtils.isEmpty(originAccount) || ObjectUtils.isEmpty(receiverAccount)) return null;

        if(originAccount.getBalance() - value < originAccount.getLimit() * (-1)) {
            originAccount.setBalance(originAccount.getBalance() - value);
            return originAccount;
        }

        checkingAccountDAO.updateExistentCheckingAccountBalanceByCheckingAccountIdAndAgencyId(originAccount.getId(),
                originAccount.getAgencyDTO().getId(), originAccount.getBalance() - value);

        checkingAccountDAO.updateExistentCheckingAccountBalanceByCheckingAccountIdAndAgencyId(receiverAccount.getId(),
                receiverAccount.getAgencyDTO().getId(), receiverAccount.getBalance() + value);

        return checkingAccountDAO.findByAgencyIdAndCheckingAccountId(originAccount.getAgencyDTO().getId(), originAccount.getId());
    }
}
