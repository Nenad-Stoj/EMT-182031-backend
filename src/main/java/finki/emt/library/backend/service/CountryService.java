package finki.emt.library.backend.service;

import finki.emt.library.backend.model.Country;
import finki.emt.library.backend.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();

    Optional<Country> findById(Long id);

    Optional<Country> create(CountryDto countryDto);

    void delete(Long id);
}
