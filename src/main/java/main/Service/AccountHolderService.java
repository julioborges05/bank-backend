package main.Service;

import main.DAO.AccountHolderDAO;
import main.DTO.AccountHolderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AccountHolderService {
    @Autowired
    private AccountHolderDAO accountHolderDao;

    public List<AccountHolderDTO> findAll() {
        return accountHolderDao.findAll();
    }

    public AccountHolderDTO findById(Integer id) {
        return accountHolderDao.findById(id);
    }

    public AccountHolderDTO findByCpf(String cpf) {
        return accountHolderDao.findByCpf(cpf);
    }

    public AccountHolderDTO createAccountHolder(AccountHolderDTO accountHolderDTO) {
        accountHolderDao.createAccountHolder(accountHolderDTO);
        return findByCpf(accountHolderDTO.getCpf());
    }

    public AccountHolderDTO createAccountHolderIfNotExists(AccountHolderDTO accountHolderDTO) {
        AccountHolderDTO accountHolder = findByCpf(accountHolderDTO.getCpf());

        return ObjectUtils.isEmpty(accountHolder) ? createAccountHolder(accountHolderDTO) : accountHolder;
    }
}
