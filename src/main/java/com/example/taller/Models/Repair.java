package com.example.taller.Models;

public class Repair {

    private int id;
    private String date;
    private String assignedMechanic;
    private String client;

    public Repair(int id, String date, String assignedMechanic, String client) {
        this.id = id;
        this.date = date;
        this.assignedMechanic = assignedMechanic;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAssignedMechanic() {
        return assignedMechanic;
    }

    public void setAssignedMechanic(String assignedMechanic) {
        this.assignedMechanic = assignedMechanic;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", assignedMechanic='" + assignedMechanic + '\'' +
                ", client='" + client + '\'' +
                '}';
    }
}
