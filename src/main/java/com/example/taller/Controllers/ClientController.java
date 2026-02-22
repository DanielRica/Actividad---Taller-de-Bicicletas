package com.example.taller.Controllers;

import com.example.taller.Models.Bike;
import com.example.taller.Models.Client;
import com.example.taller.Repositories.ClientRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;

    @FXML
    private TableView<Client> tblClients;

    @FXML
    private TableColumn<Client, String> colName;
    @FXML
    private TableColumn<Client, String> colId;
    @FXML
    private TableColumn<Client, String> colPhone;
    @FXML
    private TableColumn<Client, String> colAddress;

    private ClientRepository repository;

    @FXML
    public void initialize() {

        /*
        Se inicializa el repositorio y se vinculan los datos de la tabla
         */
        repository = ClientRepository.getInstancia();

        colName.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getName()));
        colId.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getId()));
        colPhone.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPhone()));
        colAddress.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getAdress()));

        /*
        Observador que rellena los campos cuando un cliente es seleccionado
         */
        tblClients.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> {
                    if (newV != null) {
                        txtId.setText(newV.getId());
                        txtName.setText(newV.getName());
                        txtAddress.setText(newV.getAdress());
                        txtPhone.setText(newV.getPhone());
                    }
                }
        );

        load();
    }

    /*
    carga los datos de la tabla
     */
    private void load() {
        tblClients.setItems(FXCollections.observableArrayList(repository.getAll()));
    }

    @FXML
    private void OnSave() {
        /*
        Validar campos vacíos
         */
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty()) {
            showAlert("Advertencia", "Por favor complete todos los campos", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            Client client = new Client(txtName.getText(), txtId.getText(), txtPhone.getText(), txtAddress.getText());
            repository.add(client);
            load();
            clear();
        }

        catch (NumberFormatException e) {
            showAlert("Error", "No se pudo guardar los datos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void OnEdit() {
         /*
        Validar campos vacíos
         */
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty()) {
            showAlert("Advertencia", "Por favor complete todos los campos", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            Client client = new Client(txtName.getText(), txtId.getText(), txtPhone.getText(), txtAddress.getText());
            repository.update(client);
            load();
            clear();
        }

        catch (NumberFormatException e) {
            showAlert("Error", "No se pudo modificar los datos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void OnDelete() {
        Client client = tblClients.getSelectionModel().getSelectedItem();
        if (client == null) return;

        repository.delete(client);
        load();
        clear();
    }

    private void clear() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();
    }

    private void showAlert(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
