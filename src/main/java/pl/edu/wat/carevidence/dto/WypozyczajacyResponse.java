package pl.edu.wat.carevidence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WypozyczajacyResponse {
    private String id;
    private String name;
    private String surname;
}