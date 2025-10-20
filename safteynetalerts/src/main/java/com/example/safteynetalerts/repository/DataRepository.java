package com.example.safteynetalerts.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import com.example.safteynetalerts.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Repository
public class DataRepository {

    private static final Logger log = LogManager.getLogger(DataRepository.class);
    private final ObjectMapper mapper = new ObjectMapper();


    private final List<FireStation> stations = new ArrayList<>();
    private final Map<String, Person> person = new HashMap<>();
    private final Map<String, MedicalRecord> medicalRecords = new HashMap<>();


    @PostConstruct
    public void load() {
        String fileName = "data.json";
        try (InputStream is = new ClassPathResource(fileName).getInputStream()) {
            JsonNode root = mapper.readTree(is);

//    Load fireStation Data

            JsonNode fireStationArray = root.get("firestations");
            if (fireStationArray != null && fireStationArray.isArray()) {
                List<FireStation> loadedStations = mapper.convertValue(
                        fireStationArray, new TypeReference<List<FireStation>>() {
                        });
                stations.addAll(loadedStations);
                log.info("Loaded {} fire station records", stations.size());
            }
            // Person Data
            JsonNode personArray = root.get("persons");
            if (personArray != null && personArray.isArray()) {
                List<Person> loadedPersons = mapper.convertValue(
                        personArray, new TypeReference<List<Person>>() {
                        });
                for (Person p : loadedPersons) {
                    String key = (p.getFirstName() + "|" + p.getLastName()).toLowerCase();
                    person.put(key, p);
                }
                log.info("Loaded {} person records", person.size());

            }

            // Medical Data
            JsonNode medicalArray = root.get("medicalrecords");
            if (medicalArray != null && medicalArray.isArray()) {
                List<MedicalRecord> loadedMed = mapper.convertValue(
                        medicalArray, new TypeReference<List<MedicalRecord>>() {
                        });
                for (MedicalRecord m : loadedMed) {
                    String key = (m.getFirstName() + "|" + m.getLastName()).toLowerCase();
                    medicalRecords.put(key, m);
                }
                log.info("Loaded {} medical records", medicalRecords.size());

            }
        } catch (IOException e) {
            log.error("Failed to load {} : {}", fileName, e.getMessage(), e);
        }
    }

    public List<FireStation> getStations() {
        return Collections.unmodifiableList(stations);
    }

    public List<FireStation> findByStationNumber(int stationNumber) {
        return stations.stream()
                .filter(s -> s.getStation() == stationNumber)
                .toList();
    }

    public Map<String, Person> getPerson() {
        return person;
    }

    public Optional<Person> findPerson(String firstName, String lastName) {
        String key = (firstName + "|" + lastName).toLowerCase();
        return Optional.ofNullable(person.get(key));
    }

    public Map<String, MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public Optional<MedicalRecord> findMedicalRecords(String firstName, String lastName) {
        String key = (firstName + "|" + lastName).toLowerCase();
        return Optional.ofNullable(medicalRecords.get(key));
    }
}
