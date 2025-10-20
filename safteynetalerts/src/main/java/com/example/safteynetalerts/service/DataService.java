package com.example.safteynetalerts.service;


import com.example.safteynetalerts.model.FireStation;
import com.example.safteynetalerts.model.MedicalRecord;
import com.example.safteynetalerts.model.Person;
import com.example.safteynetalerts.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DataService{

    private final DataRepository repo;

    public DataService(DataRepository repo) {
        this.repo = repo;
    }

    public List<FireStation> getAllFireStations(){
        return repo.getStations();
    }

    public List<FireStation> getByStationNumber(int stationNumber){
        return repo.findByStationNumber(stationNumber);
    }

    public List<Person> getAllPerson(){return repo.getPerson().values().stream().toList();}

    public Optional<Person> findPerson(String firstName, String lastName){
        return repo.findPerson(firstName, lastName);
    }
    public List<MedicalRecord> getAllMedicalRecords(){return repo.getMedicalRecords().values().stream().toList();}

    public Optional<MedicalRecord> findMedicalRecord(String firstName, String lastName){
        return repo.findMedicalRecords(firstName, lastName);
    }
}
