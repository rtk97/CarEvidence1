package pl.edu.wat.carevidence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.carevidence.dto.SamochodRequest;
import pl.edu.wat.carevidence.dto.SamochodResponse;
import pl.edu.wat.carevidence.dto.WypozyczajacyResponse;
import pl.edu.wat.carevidence.entity.Samochod;
import pl.edu.wat.carevidence.entity.Wypozyczajacy;
import pl.edu.wat.carevidence.exception.EntityNotFound;
import pl.edu.wat.carevidence.repository.SamochodRepository;
import pl.edu.wat.carevidence.repository.WypozyczajacyRepository;
import pl.edu.wat.carevidence.exception.EntityNotFound;

import java.util.List;
import java.util.Optional;

@Service
public class SamochodService {
    private final SamochodRepository samochodRepository;
    private final OcenaService ocenaService;
    private final WypozyczajacyRepository wypozyczajacyRepository;

    @Autowired
    public SamochodService(SamochodRepository samochodRepository, OcenaService ocenaService, WypozyczajacyRepository wypozyczajacyRepository) {
        this.samochodRepository = samochodRepository;
        this.ocenaService = ocenaService;
        this.wypozyczajacyRepository = wypozyczajacyRepository;
    }

    public SamochodResponse getSamochodById(String id) throws EntityNotFound {
        Samochod samochod = samochodRepository.findById(id).orElseThrow(EntityNotFound::new);

        Wypozyczajacy wypozyczajacy = wypozyczajacyRepository.findById(samochod.getWypozyczajacyId()).orElseThrow(EntityNotFound::new);

        return new SamochodResponse(
                samochod.getId(),
                samochod.getTitle(),
                samochod.getOcena(),
                samochod.getCena(),
                new WypozyczajacyResponse(wypozyczajacy.getId(), wypozyczajacy.getName(), wypozyczajacy.getSurname()));
    }

    public SamochodResponse save(SamochodRequest samochodRequest) throws EntityNotFound {
        Samochod samochod = new Samochod();
        samochod.setTitle(samochodRequest.getTitle());
        samochod.setCena(samochodRequest.getCena());
        samochod.setOcena(ocenaService.getOcena(samochod));

        Wypozyczajacy wypozyczajacy = wypozyczajacyRepository.findById(samochodRequest.getWypozyczajacyId())
                .orElseThrow(EntityNotFound::new);

        samochod.setWypozyczajacyId(wypozyczajacy.getId());
        samochod = samochodRepository.save(
                samochod
        );
        System.out.println(samochod);
        return new SamochodResponse(
                samochod.getId(),
                samochod.getTitle(),
                samochod.getOcena(),
                samochod.getCena(),
                new WypozyczajacyResponse(wypozyczajacy.getId(), wypozyczajacy.getName(), wypozyczajacy.getSurname()));
    }

    public List<SamochodResponse> getAll() {

        return samochodRepository.findAll()
                .stream()
                .map(this::toSamochodResponse)
                .flatMap(Optional::stream)
                .toList();
    }

    private Optional<SamochodResponse> toSamochodResponse(Samochod samochod) {
        try {
            Wypozyczajacy wypozyczajacy = wypozyczajacyRepository.findById(samochod.getWypozyczajacyId()).orElseThrow(EntityNotFound::new);
            return Optional.of(
                    new SamochodResponse(samochod.getId(), samochod.getTitle(), samochod.getOcena(),samochod.getCena(), new WypozyczajacyResponse(wypozyczajacy.getId(), wypozyczajacy.getName(), wypozyczajacy.getSurname()))
            );
        } catch (EntityNotFound e) {
            return Optional.empty();
        }
    }
    public void delete(String id) throws EntityNotFound {
        Samochod samochod = samochodRepository.findById(id).orElseThrow(EntityNotFound::new);
        samochodRepository.delete(samochod);
    }
    public void deleteAll() {
        samochodRepository.deleteAll();
    }

}