package com.example.taller.Repositories;

import com.example.taller.Models.Bike;

import java.util.ArrayList;

public class BikeRepository {

    private static BikeRepository instancia;
    private ArrayList<Bike> bikes;

    private BikeRepository() {
        bikes = new ArrayList<>();
    }

    public static BikeRepository getInstancia() {
        if (instancia == null)
            instancia = new BikeRepository();
        return instancia;
    }

    /*
    Metodo para añadir una bicicleta
     */
    public void add(Bike bike) {
        bikes.add(bike);
    }

    /*
    Modificar una bicicleta
     */
    public void update(Bike bike) {
        for (int i = 0; i < bikes.size(); i++) {
            if (bikes.get(i).getId().equals(bike.getId())) {
                bikes.set(i, bike);
                break;
            }
        }
    }

    /*
    Eliminar bicicleta
     */
    public void delete(Bike bike) {
        bikes.remove(bike);
    }

    /*
    Obtener lista completa de bicicletas
     */
    public ArrayList<Bike> getAll() {
        return bikes;
    }
}

