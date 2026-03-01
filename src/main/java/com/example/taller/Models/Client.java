package com.example.taller.Models;

public class Client extends Person{
    private String id;
    private String Phone;
    private String direction;

    public Client(String name, String id, String Phone, String direction) {
       super(name);
        this.id = id;
        this.Phone = Phone;
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getAdress() {
        return direction;
    }

    public void setAdress(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + super.getName() + '\'' +
                ", id=" + id +
                ", Phone=" + Phone +
                ", direction='" + direction + '\'' +
                '}';
    }
}
