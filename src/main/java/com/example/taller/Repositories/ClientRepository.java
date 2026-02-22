package com.example.taller.Repositories;

import com.example.taller.Models.Client;

import java.util.ArrayList;

public class ClientRepository {


        private static ClientRepository instancia;
        private ArrayList<Client> clients;

        private ClientRepository() {
            clients = new ArrayList<>();
        }

        public static ClientRepository getInstancia() {
            if (instancia == null)
                instancia = new ClientRepository();
            return instancia;
        }
        /*
        Metodo para añadir un cliente
         */
        public void add(Client client) {
            clients.add(client);
        }


        /*
        Modificar un cliente
         */
        public void update(Client client) {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).getId().equals(client.getId())) {
                    clients.set(i, client);
                    break;
                }
            }
        }


        /*
        Eliminar cliente
         */
        public void delete(Client client) {
            clients.remove(client);
        }

        /*
        Obtener lista completa de clientes
         */
        public ArrayList<Client> getAll() {
            return clients;
        }
}

