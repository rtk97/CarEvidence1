package pl.edu.wat.carevidence.dto;

import lombok.Data;
import pl.edu.wat.carevidence.entity.Wypozyczajacy;

@Data
public class SamochodRequest {
    private String title;
    private String wypozyczajacyId;
    private Integer ocena;
    private String cena;
}