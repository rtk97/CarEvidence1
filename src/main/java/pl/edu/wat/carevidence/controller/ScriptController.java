package pl.edu.wat.carevidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.carevidence.service.ScriptService;

@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @Autowired
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PutMapping()
    public ResponseEntity<String> execScript() {
        String script = """
                var samochody = samochodRepository.findAll();
                                var suma = 0;
                                var output = "";
                                samochody.forEach(function(samochod) {
                                    samochod.setCena(samochod.getCena() + " złotych");
                                    suma += samochod.getCena();
                                    output += samochod.getTitle() + ", " + samochod.getCena() + ", ";
                                });
                               
                """;

        return new ResponseEntity<>(scriptService.exec(script), HttpStatus.OK) ;
    }
}















































































