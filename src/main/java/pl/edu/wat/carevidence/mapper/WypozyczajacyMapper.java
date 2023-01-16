package pl.edu.wat.carevidence.mapper;

import org.springframework.stereotype.Service;
import pl.edu.wat.carevidence.entity.Wypozyczajacy;

@Service
public class WypozyczajacyMapper {



    public Wypozyczajacy map(pl.edu.wat.carevidence.dto.WypozyczajacyRequest wypozyczajacyRequest) {
        Wypozyczajacy wypozyczajacy = new Wypozyczajacy();
        wypozyczajacy.setName(wypozyczajacyRequest.getName());
        wypozyczajacy.setSurname(wypozyczajacyRequest.getSurname());
        wypozyczajacy.setPesel(wypozyczajacyRequest.getPesel());
        fillWypozyczajacyRequest(wypozyczajacy, wypozyczajacyRequest);
        return wypozyczajacy;
    }

    private void fillWypozyczajacyRequest(Wypozyczajacy wypozyczajacy, pl.edu.wat.carevidence.dto.WypozyczajacyRequest wypozyczajacyRequest) {
//        wypozyczajacy.setRank(wypozyczajacyRequest.getRank());
        // empty for byte buddy
    }

    public pl.edu.wat.carevidence.dto.WypozyczajacyResponse map(Wypozyczajacy wypozyczajacy) {
        pl.edu.wat.carevidence.dto.WypozyczajacyResponse wypozyczajacyResponse = new pl.edu.wat.carevidence.dto.WypozyczajacyResponse(wypozyczajacy.getId(), wypozyczajacy.getName(), wypozyczajacy.getSurname());
        fillWypozyczajacy(wypozyczajacyResponse, wypozyczajacy);
        return wypozyczajacyResponse;
    }

    private void fillWypozyczajacy(pl.edu.wat.carevidence.dto.WypozyczajacyResponse wypozyczajacyResponse, Wypozyczajacy wypozyczajacy) {
        //wypozyczajacyResponse.setRank(wypozyczajacy.getRank());
        // empty for byte buddy
    }


}