package pl.edu.wat.carevidence.service;

import org.springframework.stereotype.Service;
import pl.edu.wat.carevidence.entity.Samochod;

import java.util.Random;

@Service
public class OcenaService {
    public Integer getOcena(Samochod samochod) {
        return new Random().nextInt(10); //TODO
    }
}