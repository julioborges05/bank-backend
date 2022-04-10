package main.DAO;

import main.DTO.CheckingAccountDTO;
import main.RowMapper.CheckingAccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckingAccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CheckingAccountDTO> findAll() {
        return jdbcTemplate.query("select * from public.checking_account ca", new CheckingAccountRowMapper());
    }
}
