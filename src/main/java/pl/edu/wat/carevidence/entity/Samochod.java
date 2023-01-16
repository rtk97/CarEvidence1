package pl.edu.wat.carevidence.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class Samochod {
    @MongoId
    private String id;
    private String title;
    private Integer ocena;
    private String cena;
    private String WypozyczajacyId;

}