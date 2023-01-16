package pl.edu.wat.carevidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.carevidence.dto.SamochodRequest;
import pl.edu.wat.carevidence.dto.SamochodResponse;
import pl.edu.wat.carevidence.exception.EntityNotFound;
import pl.edu.wat.carevidence.service.SamochodService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/samochod")
public class SamochodController {
    private final SamochodService samochodService;

    @Autowired
    public SamochodController(SamochodService samochodService) {
        this.samochodService = samochodService;
    }

    @GetMapping()
    public ResponseEntity<List<SamochodResponse>> getAllSamochod() {
        List<SamochodResponse> wypozyczajacyOptional = samochodService.getAll();
        return new ResponseEntity<>(wypozyczajacyOptional, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createSamochod(@RequestBody SamochodRequest wypozyczajacyRequest) {
        try {
            return new ResponseEntity<>(samochodService.save(wypozyczajacyRequest).getId(), HttpStatus.CREATED);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSamochod(@PathVariable("id") String id) {
        try {
            samochodService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllSamochod() {
        samochodService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}