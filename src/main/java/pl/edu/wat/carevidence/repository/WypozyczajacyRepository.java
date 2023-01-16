package pl.edu.wat.carevidence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.carevidence.entity.Wypozyczajacy;

@Repository
public interface WypozyczajacyRepository extends MongoRepository<Wypozyczajacy, String> {
}