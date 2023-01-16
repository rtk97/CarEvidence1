package pl.edu.wat.carevidence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.wat.carevidence.entity.Wypozyczajacy;

@Data
@AllArgsConstructor
public class SamochodResponse {
    private String id;
    private String title;
    private Integer ocena;
    private String cena;
    private WypozyczajacyResponse wypozyczajacy;
}