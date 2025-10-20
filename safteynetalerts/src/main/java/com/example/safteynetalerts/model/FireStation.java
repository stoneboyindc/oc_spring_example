package com.example.safteynetalerts.model;

public class FireStation {
    private String address;
    private int station;

    public FireStation(int station, String address) {
        this.station = station;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public FireStation(){

    }


}
