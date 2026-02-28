package com.example.taller.Models;

public class Mechanic {
    private String name;
    private String speciality;
    private String cNumber;

    public Mechanic(String name, String speciality, String cNumber) {
        this.name = name;
        this.speciality = speciality;
        this.cNumber = cNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getcNumber() {
        return cNumber;
    }


    @Override
    public String toString() {
        return "Mechanic{" +
                "name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", cNumber='" + cNumber + '\'' +
                '}';
    }
}
