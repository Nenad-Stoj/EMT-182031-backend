package finki.emt.library.backend.service.impl;

import finki.emt.library.backend.repository.CountryRepository;
import finki.emt.library.backend.model.Country;
import finki.emt.library.backend.model.dto.CountryDto;
import finki.emt.library.backend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> create(CountryDto countryDto) {
        String name = countryDto.getName();
        String continent = countryDto.getContinent();

        if (name.isEmpty() || continent.isEmpty())
            return Optional.empty();

        Country country = new Country(name, continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
