package main.DAO;

import main.DTO.CheckingAccountDTO;
import main.RowMapper.CheckingAccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public int createCheckingAccount(CheckingAccountDTO accountDTO) {
        return jdbcTemplate.update("INSERT INTO public.checking_account (id_account_holder,id_agency,account_limit,balance, is_active) VALUES (?,?,?,?,?)",
                accountDTO.getAccountHolderDTO().getId(), accountDTO.getAgencyDTO().getId(), accountDTO.getLimit(), accountDTO.getBalance(), accountDTO.getIsActive());
    }

    public CheckingAccountDTO findByCheckingAccountParameters(CheckingAccountDTO checkingAccountDTO) {
        try {
            return jdbcTemplate.queryForObject("select * from public.checking_account ca " +
                            " where ca.id_account_holder = ? and ca.id_agency = ? and ca.account_limit = ? " +
                            " and ca.balance = ? and ca.is_active = ?",
                    new CheckingAccountRowMapper(), checkingAccountDTO.getAccountHolderDTO().getId(),
                    checkingAccountDTO.getAgencyDTO().getId(), checkingAccountDTO.getLimit(),
                    checkingAccountDTO.getBalance(), checkingAccountDTO.getIsActive());
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CheckingAccountDTO findByAgencyIdAndCheckingAccountId(Integer agencyId, Integer checkingAccountId) {
        try {
            return jdbcTemplate.queryForObject("select * from public.checking_account ca " +
                    " where ca.id_agency = ? and ca.id = ? ", new CheckingAccountRowMapper(), agencyId, checkingAccountId);
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int updateExistentCheckingAccountBalanceByCheckingAccountIdAndAgencyId(Integer checkingAccountId, Integer agencyId, Double balanceValue) {
        return jdbcTemplate.update("update public.checking_account  set balance = ? where id = ? and id_agency = ?", balanceValue, checkingAccountId, agencyId);
    }

    public List<CheckingAccountDTO> findAllCheckingAccountsByAccountHolderId(Integer accountHolderId) {
        return jdbcTemplate.query("select * from public.checking_account ca where ca.id_account_holder = ?", new CheckingAccountRowMapper(), accountHolderId);
    }
}
