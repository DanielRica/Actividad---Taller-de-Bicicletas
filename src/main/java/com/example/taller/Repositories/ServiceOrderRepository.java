package com.example.taller.Repositories;

import com.example.taller.Models.ServiceOrder;

import java.util.ArrayList;
import java.util.List;


public class ServiceOrderRepository {
    private static ServiceOrderRepository instancia;
    private ArrayList<ServiceOrder> serviceOrders;

    private ServiceOrderRepository() {
        serviceOrders = new ArrayList<>();
    }
    public static ServiceOrderRepository getInstancia() {
        if (instancia == null) {
            instancia = new ServiceOrderRepository();
        }
        return instancia;
    }
    /*
    Metodo para añadir una orden
    */
    public void add(ServiceOrder serviceOrder) {
        serviceOrders.add(serviceOrder);
    }
    /*
    Modificar una orden
    */
    public void update(ServiceOrder serviceOrder) {
        for (int i = 0; i < serviceOrders.size(); i++) {
            if (serviceOrders.get(i).getId() == serviceOrder.getId()) {
                serviceOrders.set(i, serviceOrder);
                break;
            }
        }
    }
    /*
     Eliminar orden
    */
    public void delete(ServiceOrder serviceOrder) {
        serviceOrders.remove(serviceOrder);
    }
    /*
    Obtener lista completa de ordenes
    */
    public List<ServiceOrder> getAll() {
        return serviceOrders;
    }
}
