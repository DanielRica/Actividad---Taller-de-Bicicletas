package com.example.taller.Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceOrder {
    private int id;
    private LocalDate entryDate;
    private LocalTime entryTime;
    private Bike bike;
    private Mechanic mechanic;
    private String motive;
    private String diagnosis;
    private String workPerformed;
    private double totalCost;
    private int contador = 0;

    public ServiceOrder(LocalDate entryDate, LocalTime entryTime, Bike bike, Mechanic mechanic, String serviceReason, String diagnosis, String workPerformed, double totalCost) {
        this.id = contador++;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.bike = bike;
        this.mechanic = mechanic;
        this.motive = serviceReason;
        this.diagnosis = diagnosis;
        this.workPerformed = workPerformed;
        this.totalCost = totalCost;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }

    public LocalTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalTime entryTime) { this.entryTime = entryTime; }

    public Bike getBike() { return bike; }
    public void setBike(Bike bike) { this.bike = bike; }

    public Mechanic getMechanic() { return mechanic; }
    public void setMechanic(Mechanic mechanic) { this.mechanic = mechanic; }

    public String getMotive() { return motive; }
    public void setMotive(String motive) { this.motive = motive; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getWorkPerformed() { return workPerformed; }
    public void setWorkPerformed(String workPerformed) { this.workPerformed = workPerformed; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    @Override
    public String toString() {
        return "ServiceOrder{" +
                "id=" + id +
                ", date=" + entryDate +
                ", time=" + entryTime +
                ", bike=" + bike.getId() +
                ", mechanic=" + mechanic.getName() +
                ", totalCost=" + totalCost +
                '}';
    }
}
