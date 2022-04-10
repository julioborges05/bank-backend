package main.DAO;

import main.DTO.AccountHolderDTO;
import main.RowMapper.AccountHolderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountHolderDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AccountHolderDTO> findAll() {
        return jdbcTemplate.query("select * from public.account_holder ah", new AccountHolderRowMapper());
    }

    public AccountHolderDTO findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from public.account_holder ah where ah.id = ?", new AccountHolderRowMapper(), id);
    }

    public AccountHolderDTO findByCpf(String cpf) {
        try {
            return jdbcTemplate.queryForObject("select * from public.account_holder ah where ah.cpf = ?", new AccountHolderRowMapper(), cpf);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int createAccountHolder(AccountHolderDTO accountHolderDTO) {
        return jdbcTemplate.update("INSERT INTO public.account_holder (name,cpf,birthday) VALUES (?,?,?)",
                accountHolderDTO.getName(), accountHolderDTO.getCpf(), accountHolderDTO.getBirthDay());
    }
}
