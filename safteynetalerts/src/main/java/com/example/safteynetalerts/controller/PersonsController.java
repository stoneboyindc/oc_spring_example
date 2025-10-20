package com.example.safteynetalerts.controller;

import com.example.safteynetalerts.model.FireStation;
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
@RequestMapping("/persons")
public class PersonsController {

    private final DataService service;

    public PersonsController(DataService service) {
        this.service = service;
    }

    @GetMapping("/mapping")
    public List<Person> allMappings(){
        return service.getAllPerson();
    }

    @GetMapping(params = {"firstName","lastName"})
    public ResponseEntity<Person> one(@RequestParam String firstName, String lastName){
        return service.findPerson(firstName, lastName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
