package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import liquibase.pro.packaged.C;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounterService {

    private final CounterRepository counterRepository;
    private static final Logger LOGGER = Logger.getLogger(CounterService.class.getName());

    /***
     * TODO
     * Licznik moze sie tylko zwiekszac - jezeli wpiszesz licznik z nową datą to licznik musi byc wiekszy niz poprzedni
     * to samo z datą, jak wpisujesz datę wcześniejszą niż jest ostatnia - to licznik nie może być wyższy
     */


    public Counter findLastCounterByDeviceSerialNumber(String serialNumber) {
        Counter counterList = counterRepository.getFirstByDevice_SerialNumberOrderByCounterDateDesc(serialNumber).get();
        return counterList;
    }

    /***
     * Dodaj logike aby sprawdzil ostatni counter ale do daty ktora chcesz wrzucic
     * Metoda musi rowniez sprawdzic nastepny counter = check
     * */
    
    public Counter save(Counter counter) {
        Counter lastCounter = findLastCounterByDeviceSerialNumber(counter.getDevice().getSerialNumber());
        LocalDate lastCounterDate = lastCounter.getCounterDate();
        Integer lastMonoCounter = lastCounter.getMonoCounter();
        Integer lastColourCounter = lastCounter.getColorCounter();
        if (counter.getCounterDate().isBefore(lastCounterDate) && (counter.getColorCounter() <= lastColourCounter && counter.getMonoCounter() <= lastMonoCounter)) {
            Counter counterSaved = counterRepository.save(counter);
            return counterSaved;
        } else {
            LOGGER.info("blad");
            return new Counter();
        }
    }


    public Counter findById(Long id) {
        Optional<Counter> counterOptional = counterRepository.findById(id);
        return counterOptional.orElse(new Counter());
    }

    public boolean delete(Long id) {
        Optional<Counter> counter = counterRepository.findById(id);
        if (counter.isPresent()) {
            counterRepository.delete(counter.get());
            return true;
        } else {
            return false;
        }
    }

    //todo UPDATE


}
