package com.example.taller.Repositories;

import com.example.taller.Models.Client;
import com.example.taller.Models.Mechanic;

import java.util.ArrayList;

public class MechanicRepository {
        private static MechanicRepository instancia;
        private ArrayList<Mechanic> mechanics;

        private MechanicRepository() {
            mechanics = new ArrayList<>();
        }

        public static MechanicRepository getInstancia() {
            if (instancia == null)
                instancia = new MechanicRepository();
            return instancia;
        }
        /*
        Metodo para añadir un mecánico
         */
        public void add(Mechanic mechanic) {
            mechanics.add(mechanic);
        }
        /*
        Modificar un mecánico
         */
        public void update(Mechanic mechanic) {
            for (int i = 0; i < mechanics.size(); i++) {
                if (mechanics.get(i).getcNumber().equals(mechanic.getcNumber())) {
                    mechanics.set(i, mechanic);
                    break;
                }
            }
        }
        /*
        Eliminar mecánico
         */
        public void delete(Mechanic mechanic) {
            mechanics.remove(mechanic);
        }

        /*
        Obtener lista completa de mecánicos
         */
        public ArrayList<Mechanic> getAll() {
            return mechanics;
        }
}

