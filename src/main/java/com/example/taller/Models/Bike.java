package com.example.taller.Models;

public class Bike {

    private String brand;
    private String type;
    private String color;
    private String id;
    private String year;
    private Client owner;

    public Bike(String brand, String type, String color, String id, String year, Client owner) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.id = id;
        this.year = year;
        this.owner = owner;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", id='" + id + '\'' +
                ", year='" + year + '\'' +
                ", owner=" + owner +
                '}';
    }
}
