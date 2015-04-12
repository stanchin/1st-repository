package com.tsystems.javaschool.dto;


public class ClientNumberDTO {
    private String name;
    private String clientSurname;
    private Long clientPassport;
    private String clientEmail;
    private Long clientNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public Long getClientPassport() {
        return clientPassport;
    }

    public void setClientPassport(Long clientPassport) {
        this.clientPassport = clientPassport;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Long getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Long clientNumber) {
        this.clientNumber = clientNumber;
    }
}
