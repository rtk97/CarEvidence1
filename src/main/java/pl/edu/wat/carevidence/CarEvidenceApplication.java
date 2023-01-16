package pl.edu.wat.carevidence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.carevidence.reflection.Reflection;
import pl.edu.wat.carevidence.reflection.FieldInformation;

@SpringBootApplication
public class CarEvidenceApplication {

    public static void main(String[] args) throws Exception {
        FieldInformation fieldInformation = new FieldInformation();
        Reflection.apply(fieldInformation.getFieldName(),fieldInformation.getFieldType());
        SpringApplication.run(CarEvidenceApplication.class, args);
    }

}