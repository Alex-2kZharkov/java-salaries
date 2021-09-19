package com.example.salaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TableView<PaymentEntity> paymentsTable;

    @FXML
    private TableColumn<PaymentEntity, Integer> id;

    @FXML
    private TableColumn<PaymentEntity, String> name;

    @FXML
    private TableColumn<PaymentEntity, String> address;

    @FXML
    private TableColumn<PaymentEntity, String> phone;

    @FXML
    private TableColumn<PaymentEntity, String> positionName;

    @FXML
    private TableColumn<PaymentEntity, Double> profit_tax;

    @FXML
    private TableColumn<PaymentEntity, Double> prof_tax;

    @FXML
    private TableColumn<PaymentEntity, Double> retirement_tax;

    @FXML
    private TableColumn<PaymentEntity, LocalDateTime> date;

    @FXML
    private TableColumn<PaymentEntity, Double> total;

    @FXML
    private TextField profitTaxField;

    @FXML
    private TextField profTaxField;

    @FXML
    private TextField retirementTaxField;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button countButton;

    @FXML
    private TextField totalPaiedField;

    @FXML
    private TextField calendarDaysField;

    @FXML
    private TextField workDaysField;

    @FXML
    private TextField benefitMoneyField;

    public void setPaymentsTable() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getDatabaseLink();
        ObservableList<PaymentEntity> payments  = dbConnection.getPayments(connection);
        paymentsTable.setItems(payments);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        positionName.setCellValueFactory(new PropertyValueFactory<>("positionName"));
        profit_tax.setCellValueFactory(new PropertyValueFactory<>("profit_tax"));
        prof_tax.setCellValueFactory(new PropertyValueFactory<>("prof_tax"));
        retirement_tax.setCellValueFactory(new PropertyValueFactory<>("retirement_tax"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            setPaymentsTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}