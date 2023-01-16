package pl.edu.wat.carevidence.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class Wypozyczajacy {
    @MongoId
    private String id;
    private String surname;
    private String name;
    private String pesel;
}