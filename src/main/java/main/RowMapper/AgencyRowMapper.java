package main.RowMapper;

import main.DTO.AgencyDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgencyRowMapper implements RowMapper<AgencyDTO> {
    @Override
    public AgencyDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AgencyDTO agencyDTO = new AgencyDTO();

        agencyDTO.setId(!ObjectUtils.isEmpty(
                rs.getObject("id")) ? (Integer) rs.getObject("id") : null);

        agencyDTO.setName(!ObjectUtils.isEmpty(
                rs.getObject("name")) ? (String) rs.getObject("name") : null);

        agencyDTO.setAddress(!ObjectUtils.isEmpty(
                rs.getObject("address")) ? (String) rs.getObject("address") : null);

        return agencyDTO;
    }
}
