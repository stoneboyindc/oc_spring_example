package com.example.safteynetalerts.controller;

import com.example.safteynetalerts.model.FireStation;
import com.example.safteynetalerts.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {
    private final DataService service;

    public FireStationController(DataService service) {
        this.service = service;
    }

    @GetMapping("/mapping")
    public List<FireStation> allMappings(){
        return service.getAllFireStations();
    }
    @GetMapping(params = "stationNumber")
    public List<FireStation> byStation(@RequestParam int stationNumber){
        return service.getByStationNumber(stationNumber);
    }
}
