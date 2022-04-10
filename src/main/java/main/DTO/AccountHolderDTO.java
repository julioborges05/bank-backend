package main.DTO;

import java.util.Date;

public class AccountHolderDTO {
    private Integer id;
    private String name;
    private String CPF;
    private Date birthDay;

    public AccountHolderDTO() {
    }

    public AccountHolderDTO(int id, String name, String CPF, Date birthDay) {
        this.id = id;
        this.name = name;
        this.CPF = CPF;
        this.birthDay = birthDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
