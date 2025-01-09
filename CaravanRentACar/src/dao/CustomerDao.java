package dao;

import database.DatabaseConnection;
import models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CrudDAO<Customer, String> {
    @Override
    public boolean add(Customer customer) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getCustId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getDob());
            ps.setDouble(4, customer.getSalary());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getCity());
            ps.setString(7, customer.getProvince());
            ps.setString(8, customer.getPostalCode());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE customer SET name = ?, dob = ?, salary = ?, address = ?, city = ?, province = ?, postal_code = ? WHERE cust_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getDob());
            ps.setDouble(3, customer.getSalary());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getCity());
            ps.setString(6, customer.getProvince());
            ps.setString(7, customer.getPostalCode());
            ps.setString(8, customer.getCustId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String custId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM customer WHERE cust_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, custId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer find(String custId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM customer WHERE cust_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, custId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("cust_id"),
                        rs.getString("name"),
                        rs.getString("dob"),
                        rs.getDouble("salary"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM customer";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
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
        return customers;
    }
}
