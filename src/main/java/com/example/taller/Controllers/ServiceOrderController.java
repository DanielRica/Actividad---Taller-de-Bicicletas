package com.example.taller.Controllers;

import com.example.taller.Models.Bike;
import com.example.taller.Models.Mechanic;
import com.example.taller.Models.ServiceOrder;
import com.example.taller.Repositories.BikeRepository;
import com.example.taller.Repositories.ServiceOrderRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.taller.Repositories.MechanicRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceOrderController {

    @FXML
    private ComboBox<Bike> cbNumber1;

    @FXML
    private ComboBox<Mechanic> cbMechanic;

    @FXML
    private ComboBox<Bike> cbNumber;

    @FXML
    private TableColumn<ServiceOrder, String> colBike;

    @FXML
    private TableColumn<ServiceOrder, String> colMechanic;

    @FXML
    private TableColumn<ServiceOrder, String> colDate;

    @FXML
    private TableColumn<ServiceOrder, String> colDiagnosis;

    @FXML
    private TableColumn<ServiceOrder, String> colHour;

    @FXML
    private TableColumn<ServiceOrder, String> colMotive;

    @FXML
    private TableColumn<ServiceOrder, String> colPrice;

    @FXML
    private TableColumn<ServiceOrder, String> colWorkDone;

    @FXML
    private DatePicker dateFilter;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> hourSp;

    @FXML
    private Spinner<Integer> minuteSp;

    @FXML
    private TableView<ServiceOrder> tblDiagnosis;

    @FXML
    private TextField txtDiagnosis;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtServiceMotive;

    @FXML
    private TextField txtWorkDone;

    private ServiceOrderRepository repository;
    @FXML
    public void initialize() {
        repository = ServiceOrderRepository.getInstancia();
        /*Configurar spinners*/
        hourSp.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8));
        minuteSp.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, 0, 15));

        /*Configurar columnas*/
        colBike.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getBike().getId()));

        colMechanic.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getMechanic().getName()));

        colDate.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getEntryDate().toString()));

        colHour.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getEntryTime().toString()));

        colMotive.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getMotive()));

        colDiagnosis.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getDiagnosis()));

        colWorkDone.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getWorkPerformed()));

        colPrice.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(String.valueOf(c.getValue().getTotalCost())));

        /*Selección en tabla*/
        tblDiagnosis.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> {
                    if (newV != null) {
                        cbNumber.setValue(newV.getBike());
                        cbMechanic.setValue(newV.getMechanic());
                        datePicker.setValue(newV.getEntryDate());
                        hourSp.getValueFactory().setValue(newV.getEntryTime().getHour());
                        minuteSp.getValueFactory().setValue(newV.getEntryTime().getMinute());
                        txtServiceMotive.setText(newV.getMotive());
                        txtDiagnosis.setText(newV.getDiagnosis());
                        txtWorkDone.setText(newV.getWorkPerformed());
                        txtPrice.setText(String.valueOf(newV.getTotalCost()));
                    }
                }
        );
        /*Cargar bicicletas en combos*/
        cbNumber.setItems(FXCollections.observableArrayList(
                BikeRepository.getInstancia().getAll()
        ));
        cbNumber1.setItems(FXCollections.observableArrayList(
                BikeRepository.getInstancia().getAll()
        ));
        /*Cargar mecánicos en combo*/
        cbMechanic.setItems(FXCollections.observableArrayList(
                MechanicRepository.getInstancia().getAll()
        ));
        /*Mostrar solo id de bicicleta*/
        cbNumber.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Bike item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getId());
            }
        });
        cbNumber1.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Bike item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getId());
            }
        });
        cbNumber.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Bike item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getId());
            }
        });
        load();

        cbNumber1.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Bike item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getId());
            }
        });
        load();
        /*Mostrar solo nombre del mecánico*/
        cbMechanic.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Mechanic item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });

        cbMechanic.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Mechanic item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });


    }

    private void load() {
        tblDiagnosis.setItems(FXCollections.observableArrayList(repository.getAll()));
    }

    @FXML
    void OnSave(ActionEvent event) {
        if (datePicker.getValue() == null || cbNumber.getValue() == null || txtServiceMotive.getText().isEmpty() || cbMechanic.getValue() == null || txtDiagnosis.getText().isEmpty() || txtWorkDone.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            showAlert("Advertencia", "Complete todos los campos", Alert.AlertType.INFORMATION);
            return;
        }
        try {
            LocalDate date = datePicker.getValue();
            LocalTime time = LocalTime.of(hourSp.getValue(), minuteSp.getValue());
            double price = Double.parseDouble(txtPrice.getText());
            ServiceOrder order = new ServiceOrder(date, time, cbNumber.getValue(), cbMechanic.getValue(), txtServiceMotive.getText().trim(), txtDiagnosis.getText().trim(), txtWorkDone.getText().trim(), price);
            repository.add(order);
            load();
            clear();
        } catch (NumberFormatException e) {
            showAlert("Error", "Precio inválido", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void OnEdit(ActionEvent event) {
        ServiceOrder selected = tblDiagnosis.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        try {
            LocalDate date = datePicker.getValue();
            LocalTime time = LocalTime.of(hourSp.getValue(), minuteSp.getValue());
            double price = Double.parseDouble(txtPrice.getText());
            ServiceOrder updated = new ServiceOrder(date, time, cbNumber.getValue(), cbMechanic.getValue(), txtServiceMotive.getText().trim(), txtDiagnosis.getText().trim(), txtWorkDone.getText().trim(), price);
            updated.setId(selected.getId());
            repository.update(updated);
            load();
            clear();
        } catch (NumberFormatException e) {
            showAlert("Error", "Precio inválido", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void OnDelete(ActionEvent event) {
        ServiceOrder selected = tblDiagnosis.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        repository.delete(selected);
        load();
        clear();
    }

    @FXML
    void OnFilter(ActionEvent event) {
        LocalDate selectedDate = dateFilter.getValue();
        if (selectedDate == null) {
            showAlert("Advertencia", "Seleccione una fecha", Alert.AlertType.INFORMATION);
            return;
        }
        ObservableList<ServiceOrder> listaFiltrada =
                FXCollections.observableArrayList();

        for (ServiceOrder order : repository.getAll()) {

            if (order.getEntryDate().equals(selectedDate)) {
                listaFiltrada.add(order);
            }
        }
        tblDiagnosis.setItems(listaFiltrada);
    }

    private void clear() {
        datePicker.setValue(null);
        cbNumber.setValue(null);
        cbMechanic.setValue(null);
        txtServiceMotive.clear();
        txtDiagnosis.clear();
        txtWorkDone.clear();
        txtPrice.clear();
    }

    private void showAlert(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void OnEraseFilter(ActionEvent actionEvent) {
        dateFilter.setValue(null);
        load();
    }

    public void OnFilter2(ActionEvent actionEvent) {
        Bike selectedBike = cbNumber1.getValue();
        if (selectedBike == null) {
            showAlert("Advertencia", "Seleccione una bicicleta", Alert.AlertType.INFORMATION);
            return;
        }
        ObservableList<ServiceOrder> listaFiltrada =
                FXCollections.observableArrayList();
        for (ServiceOrder order : repository.getAll()) {
            if (order.getBike().equals(selectedBike)) {
                listaFiltrada.add(order);
            }
        }
        tblDiagnosis.setItems(listaFiltrada);
    }

    public void OnEraseFilter2(ActionEvent actionEvent) {
        cbNumber.setValue(null);
        load();
    }
}
