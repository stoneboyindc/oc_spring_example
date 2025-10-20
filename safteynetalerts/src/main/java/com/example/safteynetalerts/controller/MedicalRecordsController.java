package com.example.safteynetalerts.controller;

import com.example.safteynetalerts.model.MedicalRecord;
import com.example.safteynetalerts.model.Person;
import com.example.safteynetalerts.service.DataService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordsController {

    private final DataService service;

    public MedicalRecordsController(DataService service) {
        this.service = service;
    }

    @GetMapping("/mapping")
    public List<MedicalRecord> allMappings(){
        return service.getAllMedicalRecords();
    }

    @GetMapping(params = {"firstName","lastName"})
    public ResponseEntity<MedicalRecord> one(@RequestParam String firstName, @RequestParam String lastName){
        return service.findMedicalRecord(firstName, lastName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
