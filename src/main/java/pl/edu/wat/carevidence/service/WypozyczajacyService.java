package pl.edu.wat.carevidence.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.carevidence.entity.Wypozyczajacy;
import pl.edu.wat.carevidence.exception.EntityNotFound;

import java.util.List;
import java.util.Optional;

@Service
public class WypozyczajacyService {
    private final pl.edu.wat.carevidence.repository.WypozyczajacyRepository wypozyczajacyRepository;
    private final pl.edu.wat.carevidence.mapper.WypozyczajacyMapper wypozyczajacyMapper;

    @Autowired
    public WypozyczajacyService(pl.edu.wat.carevidence.repository.WypozyczajacyRepository wypozyczajacyRepository, pl.edu.wat.carevidence.mapper.WypozyczajacyMapper wypozyczajacyMapper) {
        this.wypozyczajacyRepository = wypozyczajacyRepository;
        this.wypozyczajacyMapper = wypozyczajacyMapper;
    }

    public Optional<pl.edu.wat.carevidence.dto.WypozyczajacyResponse> getWypozyczajacyById(String id) {
        return wypozyczajacyRepository.findById(id)
                .map(wypozyczajacyMapper::map);
    }

    public pl.edu.wat.carevidence.dto.WypozyczajacyResponse save(pl.edu.wat.carevidence.dto.WypozyczajacyRequest wypozyczajacyRequest) {
        Wypozyczajacy wypozyczajacy = wypozyczajacyMapper.map(wypozyczajacyRequest);
        wypozyczajacy = wypozyczajacyRepository.save(
                wypozyczajacy
        );
        return wypozyczajacyMapper.map(wypozyczajacy);
    }

    public List<pl.edu.wat.carevidence.dto.WypozyczajacyResponse> getAll() {
        return wypozyczajacyRepository.findAll()
                .stream()
                .map(wypozyczajacyMapper::map)
                .toList();
    }

    public pl.edu.wat.carevidence.dto.WypozyczajacyResponse update(String id, String name, String surname) throws EntityNotFound {
        Wypozyczajacy wypozyczajacy = wypozyczajacyRepository.findById(id).orElseThrow(EntityNotFound::new);
        if(StringUtils.isNotBlank(name)) {
            wypozyczajacy.setName(name);
        }

        if(StringUtils.isNotBlank(surname)) {
            wypozyczajacy.setSurname(surname);
        }

        wypozyczajacy = wypozyczajacyRepository.save(wypozyczajacy);
        return wypozyczajacyMapper.map(wypozyczajacy);
    }
    public void delete(String id) throws EntityNotFound {
        Wypozyczajacy wypozyczajacy = wypozyczajacyRepository.findById(id).orElseThrow(EntityNotFound::new);
        wypozyczajacyRepository.delete(wypozyczajacy);
    }
    public void deleteAll() {
        wypozyczajacyRepository.deleteAll();
    }

}