package main.DAO;

import main.DTO.AgencyDTO;
import main.RowMapper.AgencyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgencyDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AgencyDTO> findAll() {
        return jdbcTemplate.query("select * from public.agency a", new AgencyRowMapper());
    }
}
