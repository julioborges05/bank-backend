package main.DTO;

public class CheckingAccountDTO {
    private Integer id;
    private Integer idAccountHolder;
    private Integer idAgency;
    private Double limit;
    private Double balance;
    private String isActive;

    public CheckingAccountDTO() {
    }

    public CheckingAccountDTO(Integer id, Integer idAccountHolder, Integer idAgency, Double limit, Double balance, String isActive) {
        this.id = id;
        this.idAccountHolder = idAccountHolder;
        this.idAgency = idAgency;
        this.limit = limit;
        this.balance = balance;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAccountHolder() {
        return idAccountHolder;
    }

    public void setIdAccountHolder(Integer idAccountHolder) {
        this.idAccountHolder = idAccountHolder;
    }

    public Integer getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(Integer idAgency) {
        this.idAgency = idAgency;
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

    public String getActive() {
        return isActive;
    }

    public void setActive(String active) {
        isActive = active;
    }
}
