package main.DTO;

import java.util.Date;

public class AccountHolderDTO {
    private Integer id;
    private String name;
    private String cpf;
    private Date birthDay;

    public AccountHolderDTO() {
    }

    public AccountHolderDTO(Integer id) {
        this.id = id;
    }

    public AccountHolderDTO(int id, String name, String cpf, Date birthDay) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
