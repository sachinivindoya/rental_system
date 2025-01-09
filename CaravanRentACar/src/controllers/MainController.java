package controllers;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> custIdColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public void initialize() {
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadCustomers();
        customerTable.setItems(customerList);
    }

    private void loadCustomers() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM customer";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getString("cust_id"),
                        rs.getString("name"),
                        rs.getString("dob"),
                        rs.getDouble("salary"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
