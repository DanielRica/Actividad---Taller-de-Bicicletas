package com.example.taller.Models;

public class Mechanic extends Person{
    private String speciality;
    private String cNumber;

    public Mechanic(String name, String speciality, String cNumber) {
        super(name);
        this.speciality = speciality;
        this.cNumber = cNumber;
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
                "name='" + super.getName() + '\'' +
                ", speciality='" + speciality + '\'' +
                ", cNumber='" + cNumber + '\'' +
                '}';
    }
}
