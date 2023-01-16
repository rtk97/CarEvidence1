package pl.edu.wat.carevidence.dto;

import lombok.Data;

@Data
public class WypozyczajacyRequest {
    private String surname;
    private String name;
    private String pesel;
}