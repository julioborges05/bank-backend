package main.RowMapper;

import main.DTO.CheckingAccountDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckingAccountRowMapper implements RowMapper<CheckingAccountDTO> {
    @Override
    public CheckingAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CheckingAccountDTO checkingAccountDTO = new CheckingAccountDTO();

        checkingAccountDTO.setId(!ObjectUtils.isEmpty(
                rs.getObject("id")) ? (Integer) rs.getObject("id") : null);

        checkingAccountDTO.setIdAccountHolder(!ObjectUtils.isEmpty(
                rs.getObject("id_account_holder")) ? (Integer) rs.getObject("id_account_holder") : null);

        checkingAccountDTO.setIdAgency(!ObjectUtils.isEmpty(
                rs.getObject("id_agency")) ? (Integer) rs.getObject("id_agency") : null);

        checkingAccountDTO.setLimit(!ObjectUtils.isEmpty(
                rs.getObject("account_limit")) ? (Double) rs.getObject("account_limit") : null);

        checkingAccountDTO.setBalance(!ObjectUtils.isEmpty(
                rs.getObject("balance")) ? (Double) rs.getObject("balance") : null);

        checkingAccountDTO.setActive(!ObjectUtils.isEmpty(
                rs.getObject("is_active")) ? (String) rs.getObject("is_active") : null);

        return checkingAccountDTO;
    }
}
