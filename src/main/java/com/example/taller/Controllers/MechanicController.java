package com.example.taller.Controllers;

import com.example.taller.Models.Client;
import com.example.taller.Models.Mechanic;
import com.example.taller.Repositories.ClientRepository;
import com.example.taller.Repositories.MechanicRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MechanicController {

    @FXML
    private ComboBox<String> cbSpeciality;

    @FXML
    private TableColumn<Mechanic, String> colSpeciality;

    @FXML
    private TableColumn<Mechanic, String> colName;

    @FXML
    private TableColumn<Mechanic, String> colcNumber;

    @FXML
    private TableView<Mechanic> tblMechanics;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtcNumber;


    private MechanicRepository repository;

    @FXML
    public void initialize() {

        /*
        Se inicializa el repositorio y se vinculan los datos de la tabla
         */
        repository = MechanicRepository.getInstancia();

        colName.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getName()));
        colcNumber.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getcNumber()));
        colSpeciality.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSpeciality()));

        /*
        Observador que rellena los campos cuando un mecánico es seleccionado
         */
        tblMechanics.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> {
                    if (newV != null) {
                        txtName.setText(newV.getName());
                        cbSpeciality.setValue(newV.getSpeciality());
                        txtcNumber.setText(newV.getcNumber());
                    }
                }
        );

         /*
        Opciones del combo box
         */
        cbSpeciality.setItems(FXCollections.observableArrayList(
                "Frenos y transmisión", "Suspensión", "Ruedas y centrado", "Bicicletas eléctricas", "Sistemas hidráulicos", "Mantenimiento general", "Restauración y pintura"
        ));

        load();
    }

    /*
    carga los datos de la tabla
     */
    private void load() {
        tblMechanics.setItems(FXCollections.observableArrayList(repository.getAll()));
    }

    @FXML
    private void OnSave() {
        /*
        Validar campos vacíos
         */
        System.out.println("Guardando");
        if (txtName.getText().isEmpty() || cbSpeciality.getValue() == null || txtcNumber.getText().isEmpty()) {
            showAlert("Advertencia", "Por favor complete todos los campos", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            Mechanic mechanic = new Mechanic(txtName.getText(), cbSpeciality.getValue().trim(), txtcNumber.getText());
            repository.add(mechanic);
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
        if (txtName.getText().isEmpty() || cbSpeciality.getValue() == null || txtcNumber.getText().isEmpty()) {
            showAlert("Advertencia", "Por favor complete todos los campos", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            Mechanic mechanic = new Mechanic(txtName.getText(), cbSpeciality.getValue().trim(), txtcNumber.getText());
            repository.update(mechanic);
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
        Mechanic mechanic = tblMechanics.getSelectionModel().getSelectedItem();
        if (mechanic == null) return;

        repository.delete(mechanic);
        load();
        clear();
    }

    private void clear() {
        txtName.clear();
        cbSpeciality.setValue(null);
        txtcNumber.clear();
    }

    private void showAlert(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
