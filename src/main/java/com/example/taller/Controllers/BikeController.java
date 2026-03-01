package com.example.taller.Controllers;

import com.example.taller.Models.Bike;
import com.example.taller.Models.Client;
import com.example.taller.Repositories.BikeRepository;
import com.example.taller.Repositories.ClientRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class BikeController {

    @FXML
    private TextField txtBrand;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private TextField txtColor;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtYear;
    @FXML
    private TableView<Bike> tblBikes;
    @FXML
    private TableColumn<Bike, String> colBrand;
    @FXML
    private TableColumn<Bike, String> colType;
    @FXML
    private TableColumn<Bike, String> colColor;
    @FXML
    private TableColumn<Bike, String> colId;
    @FXML
    private TableColumn<Bike, String> colOwner;
    @FXML
    private TableColumn<Bike, String> colYear;

    private BikeRepository repository;

    @FXML
    private ComboBox<Client> ownerSelect;

    @FXML
    public void initialize() {
        /*
        Inicializa el repositorio y configura la tabla
         */
        repository = BikeRepository.getInstancia();

        colBrand.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getBrand()));
        colType.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getType()));
        colColor.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getColor()));
        colId.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getId()));
        colYear.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getYear()));
        colOwner.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getOwner().getId())
        );
        tblBikes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> {
                    if (newV != null) {
                        txtBrand.setText(newV.getBrand());
                        txtColor.setText(newV.getColor());
                        cbType.setValue(newV.getType());
                        txtId.setText(newV.getId());
                        txtYear.setText(newV.getYear());
                        ownerSelect.setValue(newV.getOwner());
                    }
                }
        );

        /*
        Opciones de los combo box
         */
        cbType.setItems(FXCollections.observableArrayList(
                "Ruta", "MTB", "Urbana", "Eléctrica", "De carrera"
        ));

        load();
        ownerSelect.setItems(FXCollections.observableArrayList(
                ClientRepository.getInstancia().getAll()
        ));
        /*
        Mostrar solo id cliente
         */
        ownerSelect.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getId());
            }
        });

        ownerSelect.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getId());
            }
        });
    }

    /*
    Carga la tabla
     */
    private void load() {
        tblBikes.setItems(FXCollections.observableArrayList(repository.getAll()));
    }

    @FXML
    private void OnSave() {
         /*
        Validar campos vacíos
         */
        if (txtBrand.getText().isEmpty() || cbType.getValue()== null || txtColor.getText().isEmpty() || txtId.getText().isEmpty() ||  txtYear.getText().isEmpty() || ownerSelect.getValue()== null) {
            showAlert("Advertencia", "Por favor complete todos los campos", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            Bike bike = new Bike(txtBrand.getText().trim(), cbType.getValue(), txtColor.getText().trim(), txtId.getText().trim(), txtYear.getText().trim(), ownerSelect.getValue());
            repository.add(bike);
            load();
            clear();

        } catch (NumberFormatException e) {
            showAlert("Error", "No se pudo guardar los datos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void OnEdit() {
        /*
        Validar campos vacíos
         */
        if (txtBrand.getText().isEmpty() || cbType.getValue()== null || txtColor.getText().isEmpty() || txtId.getText().isEmpty() || txtYear.getText().isEmpty() ) {
            showAlert("Advertencia", "Por favor complete todos los campos", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            Bike bike = new Bike(txtBrand.getText().trim(), cbType.getValue().trim(), txtColor.getText().trim(), txtId.getText().trim(), txtYear.getText().trim(), ownerSelect.getValue());
            repository.update(bike);
            load();
            clear();

        } catch (NumberFormatException e) {
            showAlert("Error", "No se pudo modificar los datos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void OnDelete() {
        Bike bike = tblBikes.getSelectionModel().getSelectedItem();
        if (bike == null) return;

        repository.delete(bike);
        load();
        clear();
    }

    private void clear() {
        txtId.clear();
        txtBrand.clear();
        txtColor.clear();
        cbType.setValue(null);
        txtYear.clear();
        ownerSelect.setValue(null);
    }

    private void showAlert(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
