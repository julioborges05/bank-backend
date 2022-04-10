package main.DAO;

import main.DTO.AccountHolderDTO;
import main.RowMapper.AccountHolderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountHolderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AccountHolderDTO> findAll() {
        return jdbcTemplate.query("select * from public.account_holder ah", new AccountHolderRowMapper());
    }
}
