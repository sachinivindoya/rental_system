package dao;

import database.DatabaseConnection;
import models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements CrudDAO<Vehicle, String> {
    @Override
    public boolean add(Vehicle vehicle) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO vehicle VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, vehicle.getRegId());
            ps.setString(2, vehicle.getVehicleType());
            ps.setString(3, vehicle.getDescription());
            ps.setDouble(4, vehicle.getPricePerDay());
            ps.setDouble(5, vehicle.getPricePerExcessMileage());
            ps.setInt(6, vehicle.getVehicleQtyOnHand());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Vehicle vehicle) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE vehicle SET vehicle_type = ?, description = ?, price_per_day = ?, price_per_excess_mileage = ?, vehicle_qty_on_hand = ? WHERE reg_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, vehicle.getVehicleType());
            ps.setString(2, vehicle.getDescription());
            ps.setDouble(3, vehicle.getPricePerDay());
            ps.setDouble(4, vehicle.getPricePerExcessMileage());
            ps.setInt(5, vehicle.getVehicleQtyOnHand());
            ps.setString(6, vehicle.getRegId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String regId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM vehicle WHERE reg_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, regId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Vehicle find(String regId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM vehicle WHERE reg_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, regId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Vehicle(
                        rs.getString("reg_id"),
                        rs.getString("vehicle_type"),
                        rs.getString("description"),
                        rs.getDouble("price_per_day"),
                        rs.getDouble("price_per_excess_mileage"),
                        rs.getInt("vehicle_qty_on_hand")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM vehicle";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getString("reg_id"),
                        rs.getString("vehicle_type"),
                        rs.getString("description"),
                        rs.getDouble("price_per_day"),
                        rs.getDouble("price_per_excess_mileage"),
                        rs.getInt("vehicle_qty_on_hand")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}
