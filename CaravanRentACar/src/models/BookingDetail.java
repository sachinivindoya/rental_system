package models;

public class BookingDetail {
    private String bookingId;
    private String regId;
    private int vehicleQty;
    private int discount;

    // Constructor
    public BookingDetail(String bookingId, String regId, int vehicleQty, int discount) {
        this.bookingId = bookingId;
        this.regId = regId;
        this.vehicleQty = vehicleQty;
        this.discount = discount;
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public int getVehicleQty() {
        return vehicleQty;
    }

    public void setVehicleQty(int vehicleQty) {
        this.vehicleQty = vehicleQty;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}

