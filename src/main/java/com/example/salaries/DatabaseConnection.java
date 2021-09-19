package com.example.salaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getDatabaseLink() {
        String databaseName = "salaries_db";
        String databaseUserName = "root";
        String databasePassword = "2000-2000";
        String databaseUrl = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);

        } catch (Exception err) {
            err.printStackTrace();
        }
        return databaseLink;
    }

    public ObservableList<PaymentEntity> getPayments(Connection connection) throws SQLException {
        try {
            ObservableList<PaymentEntity> payments = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("" +
                    "SELECT payments.id, employees.name, employees.address, employees.phone, positions.name as positionName, payments.profit_tax, payments.prof_tax, payments.retirement_tax, payments.date, payments.total " +
                    "FROM payments " +
                    "LEFT JOIN employees ON payments.employeeId = employees.id " +
                    "LEFT JOIN positions ON employees.positionId = positions.id");

            while (resultSet.next()) {
                PaymentEntity payment  = new PaymentEntity(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("positionName"),
                        Double.parseDouble(resultSet.getString("profit_tax")),
                        Double.parseDouble(resultSet.getString("prof_tax")),
                        Double.parseDouble(resultSet.getString("retirement_tax")),
                        LocalDateTime.parse(resultSet.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        Double.parseDouble(resultSet.getString("total")));
                payments.add(payment);
            }
            return payments;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }

    }
    public ResultSet getEmployees(Connection connection) throws SQLException {
        ObservableList<PaymentEntity> payments = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("" +
                "SELECT employees.id, positions.salary " +
                "FROM employees " +
                "LEFT JOIN positions ON employees.positionId = positions.id;");

        return resultSet;
    }

    public void createPayments(Connection connection, ResultSet employeesResultSet, Double profit_tax, Double prof_tax, Double retirement_tax, LocalDateTime date) {

    }
}
