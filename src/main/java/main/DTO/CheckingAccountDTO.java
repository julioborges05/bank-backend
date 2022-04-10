package main.DTO;

public class CheckingAccountDTO {
    private Integer id;
    private AccountHolderDTO accountHolderDTO;
    private AgencyDTO agencyDTO;
    private Double limit;
    private Double balance;
    private String isActive;

    public CheckingAccountDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountHolderDTO getAccountHolderDTO() {
        return accountHolderDTO;
    }

    public void setAccountHolderDTO(AccountHolderDTO accountHolderDTO) {
        this.accountHolderDTO = accountHolderDTO;
    }

    public AgencyDTO getAgencyDTO() {
        return agencyDTO;
    }

    public void setAgencyDTO(AgencyDTO agencyDTO) {
        this.agencyDTO = agencyDTO;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
