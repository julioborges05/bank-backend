package main.Service;

import main.DAO.AccountHolderDAO;
import main.DTO.AccountHolderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService {
    @Autowired
    private AccountHolderDAO accountHolderDao;

    public List<AccountHolderDTO> findAll() {
        return accountHolderDao.findAll();
    }
}
