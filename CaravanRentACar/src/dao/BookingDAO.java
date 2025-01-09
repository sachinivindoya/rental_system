package dao;

import database.DatabaseConnection;
import models.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDAO implements CrudDAO<Booking, String> {
    @Override
    public boolean add(Booking booking) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO booking VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, booking.getBookingId());
            ps.setDate(2, new java.sql.Date(booking.getDate().getTime()));
            ps.setString(3, booking.getCustomerId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Booking booking) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE booking SET date = ?, customer_id = ? WHERE booking_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(booking.getDate().getTime()));
            ps.setString(2, booking.getCustomerId());
            ps.setString(3, booking.getBookingId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String bookingId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM booking WHERE booking_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Booking find(String bookingId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM booking WHERE booking_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Booking(
                        rs.getString("booking_id"),
                        rs.getDate("date"),
                        rs.getString("customer_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM booking";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getString("booking_id"),
                        rs.getDate("date"),
                        rs.getString("customer_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
