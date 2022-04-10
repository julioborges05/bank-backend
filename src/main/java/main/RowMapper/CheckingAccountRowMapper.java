package main.RowMapper;

import main.DTO.AccountHolderDTO;
import main.DTO.AgencyDTO;
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

        checkingAccountDTO.setAccountHolderDTO(!ObjectUtils.isEmpty(
                rs.getObject("id_account_holder")) ? new AccountHolderDTO((Integer) rs.getObject("id_account_holder")) : null);

        checkingAccountDTO.setAgencyDTO(!ObjectUtils.isEmpty(
                rs.getObject("id_agency")) ? new AgencyDTO((Integer) rs.getObject("id_agency")) : null);

        checkingAccountDTO.setLimit(!ObjectUtils.isEmpty(
                rs.getObject("account_limit")) ? (Double) rs.getObject("account_limit") : null);

        checkingAccountDTO.setBalance(!ObjectUtils.isEmpty(
                rs.getObject("balance")) ? (Double) rs.getObject("balance") : null);

        checkingAccountDTO.setIsActive(!ObjectUtils.isEmpty(
                rs.getObject("is_active")) ? (String) rs.getObject("is_active") : null);

        return checkingAccountDTO;
    }
}
