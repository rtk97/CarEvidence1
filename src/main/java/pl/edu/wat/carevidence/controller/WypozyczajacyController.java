package pl.edu.wat.carevidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.carevidence.exception.EntityNotFound;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/Wypozyczajacy")
public class WypozyczajacyController {
    private final pl.edu.wat.carevidence.service.WypozyczajacyService wypozyczajacyService;

    @Autowired
    public WypozyczajacyController(pl.edu.wat.carevidence.service.WypozyczajacyService wypozyczajacyService) {
        this.wypozyczajacyService = wypozyczajacyService;
    }

    @GetMapping()
    public ResponseEntity<List<pl.edu.wat.carevidence.dto.WypozyczajacyResponse>> getAllWypozyczajacy() {
        List<pl.edu.wat.carevidence.dto.WypozyczajacyResponse> wypozyczajacyOptional = wypozyczajacyService.getAll();
        return new ResponseEntity<>(wypozyczajacyOptional, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<pl.edu.wat.carevidence.dto.WypozyczajacyResponse> getWypozyczajacyByIdVar(@PathVariable String id) {
        Optional<pl.edu.wat.carevidence.dto.WypozyczajacyResponse> wypozyczajacyOptional = wypozyczajacyService.getWypozyczajacyById(id);
        if (wypozyczajacyOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wypozyczajacyOptional.get(), HttpStatus.OK);
    }

    @GetMapping("{id}/surname")
    public ResponseEntity<String> getWypozyczajacySurnameById(@PathVariable String id) {
        Optional<pl.edu.wat.carevidence.dto.WypozyczajacyResponse> wypozyczajacyOptional = wypozyczajacyService.getWypozyczajacyById(id);
        if (wypozyczajacyOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wypozyczajacyOptional.get().getSurname(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createWypozyczajacy(@RequestBody pl.edu.wat.carevidence.dto.WypozyczajacyRequest wypozyczajacyRequest) {
        return new ResponseEntity<>(wypozyczajacyService.save(wypozyczajacyRequest).getId(), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<pl.edu.wat.carevidence.dto.WypozyczajacyResponse> updateWypozyczajacy(@PathVariable String id, @RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        try {
            return new ResponseEntity<>(wypozyczajacyService.update(id, name, surname), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWypozyczajacy(@PathVariable("id") String id) {
        try {
            wypozyczajacyService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllWypozyczajacy() {
        wypozyczajacyService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}