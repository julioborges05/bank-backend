package main.Service;

import main.DAO.CheckingAccountDAO;
import main.DTO.CheckingAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingAccountService {
    @Autowired
    private CheckingAccountDAO checkingAccountDAO;

    public List<CheckingAccountDTO> findAll() {
        return checkingAccountDAO.findAll();
    }
}
