package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Manufacturer;
import com.tytanisukcesu.copiers.repository.ManufacturerRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private static final Logger LOGGER = Logger.getLogger(ManufacturerService.class.getName());

    public List<Manufacturer> findAll() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return manufacturers;
    }

    public Manufacturer findById(Long id){
        return manufacturerRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id,"manufacturer"));
    }

    public boolean delete(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        if (manufacturer.isPresent()){
            manufacturerRepository.delete(manufacturer.get());
            LOGGER.info("Manufacturer for id " + id + " has been deleted");
            return true;
        } else {
            LOGGER.warning("Manufacturer for id " + id + " has not been deleted");
            return false;
        }
    }

    public Manufacturer save(Manufacturer manufacturer) {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.getManufacturerByName(manufacturer.getName());
        if (manufacturerOptional.isPresent()){
            return manufacturerOptional.get();
        } else {
            Manufacturer manufacturerSaved = manufacturerRepository.save(manufacturer);
            LOGGER.info("A new row has been added.");
            return manufacturerSaved;
        }
    }

    @Transactional
    public Manufacturer update(Long id, Manufacturer manufacturer) {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);
        if(manufacturerOptional.isPresent()){
            Manufacturer manufacturerUpdated = manufacturerOptional.get();
            manufacturerUpdated.setName(manufacturer.getName());
            manufacturerUpdated.setArticles(manufacturer.getArticles());
            manufacturerUpdated.setModels(manufacturer.getModels());
            LOGGER.info(manufacturer.getName() + " for id " + manufacturerUpdated.getId() + " has been updated.");
            return manufacturerUpdated;
        }else{
            LOGGER.warning("Manufacturer for id " + id + " has not been found");
            throw new ModelNotFoundException(id,"manufacturer");
        }
    }

    public List<Manufacturer> findAllByName(String name) {
        return manufacturerRepository.getAllByName(name);
    }
}
