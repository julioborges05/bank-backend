package main.RowMapper;

import main.DTO.AccountHolderDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AccountHolderRowMapper implements RowMapper<AccountHolderDTO> {
    @Override
    public AccountHolderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountHolderDTO accountHolderDTO = new AccountHolderDTO();

        accountHolderDTO.setId(!ObjectUtils.isEmpty(
                rs.getObject("id")) ? (Integer) rs.getObject("id") : null);

        accountHolderDTO.setName(!ObjectUtils.isEmpty(
                rs.getObject("name")) ? (String) rs.getObject("name") : null);

        accountHolderDTO.setCpf(!ObjectUtils.isEmpty(
                rs.getObject("cpf")) ? (String) rs.getObject("cpf") : null);

        accountHolderDTO.setBirthDay(!ObjectUtils.isEmpty(
                rs.getObject("birthDay")) ? (Date) rs.getObject("birthDay") : null);

        return accountHolderDTO;
    }
}
