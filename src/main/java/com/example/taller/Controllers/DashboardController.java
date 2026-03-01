package com.example.taller.Controllers;

import com.example.taller.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {
    @FXML
    private AnchorPane mainContent;

    @FXML
    private void OnGoClients() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/taller/client.fxml"));
            Parent clients = loader.load();

            /*
            Obtener el controlador de Clientes
             */
            ClientController controller = loader.getController();

            /*
            Reemplazar el contenido del contenedor principal
             */
            mainContent.getChildren().clear();
            mainContent.getChildren().add(clients);
            VBox.setVgrow(clients, Priority.ALWAYS);

        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la lista de Clientes", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void OnGoBikes() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/taller/bike.fxml"));
            Parent bikes = loader.load();

            /*
            Obtener el controlador de Bicicletas
             */
            BikeController controller = loader.getController();

            /*
            Reemplazar el contenido del contenedor principal
             */
            mainContent.getChildren().clear();
            mainContent.getChildren().add(bikes);
            VBox.setVgrow(bikes, Priority.ALWAYS);

        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la lista de Bicicletas", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void OnGoMechanics() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/taller/mechanic.fxml"));
            Parent mechanics = loader.load();

            /*
            Obtener el controlador de Mecánicos
             */
            MechanicController controller = loader.getController();

            /*
            Reemplazar el contenido del contenedor principal
             */
            mainContent.getChildren().clear();
            mainContent.getChildren().add(mechanics);
            VBox.setVgrow(mechanics, Priority.ALWAYS);

        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la lista de mecánicos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void OnGoServiceOrder() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/taller/serviceOrder.fxml"));
            Parent orders = loader.load();

            /*
            Obtener el controlador de servicios
             */
            ServiceOrderController controller = loader.getController();

            /*
            Reemplazar el contenido del contenedor principal
             */
            mainContent.getChildren().clear();
            mainContent.getChildren().add(orders);
            VBox.setVgrow(orders, Priority.ALWAYS);

        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la lista de ordenes", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void showAlert(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}




