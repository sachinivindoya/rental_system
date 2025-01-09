package dao;

import database.DatabaseConnection;
import models.BookingDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingDetailDAO implements CrudDAO<BookingDetail, String> {

    @Override
    public boolean add(BookingDetail bookingDetail) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO booking_detail VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookingDetail.getBookingId());
            ps.setString(2, bookingDetail.getRegId());
            ps.setInt(3, bookingDetail.getVehicleQty());
            ps.setInt(4, bookingDetail.getDiscount());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(BookingDetail bookingDetail) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE booking_detail SET vehicle_qty = ?, discount = ? WHERE booking_id = ? AND reg_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingDetail.getVehicleQty());
            ps.setInt(2, bookingDetail.getDiscount());
            ps.setString(3, bookingDetail.getBookingId());
            ps.setString(4, bookingDetail.getRegId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String bookingId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM booking_detail WHERE booking_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String bookingId, String regId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM booking_detail WHERE booking_id = ? AND reg_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookingId);
            ps.setString(2, regId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BookingDetail find(String bookingId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM booking_detail WHERE booking_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BookingDetail(
                        rs.getString("booking_id"),
                        rs.getString("reg_id"),
                        rs.getInt("vehicle_qty"),
                        rs.getInt("discount")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BookingDetail> findAll() {
        List<BookingDetail> bookingDetails = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM booking_detail";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookingDetails.add(new BookingDetail(
                        rs.getString("booking_id"),
                        rs.getString("reg_id"),
                        rs.getInt("vehicle_qty"),
                        rs.getInt("discount")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingDetails;
    }
}
