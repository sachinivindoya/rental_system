package models;

public class Vehicle {
    private String regId;
    private String vehicleType;
    private String description;
    private double pricePerDay;
    private double pricePerExcessMileage;
    private int vehicleQtyOnHand;

    // Constructor
    public Vehicle(String regId, String vehicleType, String description, double pricePerDay, double pricePerExcessMileage, int vehicleQtyOnHand) {
        this.regId = regId;
        this.vehicleType = vehicleType;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.pricePerExcessMileage = pricePerExcessMileage;
        this.vehicleQtyOnHand = vehicleQtyOnHand;
    }

    // Getters and Setters
    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getPricePerExcessMileage() {
        return pricePerExcessMileage;
    }

    public void setPricePerExcessMileage(double pricePerExcessMileage) {
        this.pricePerExcessMileage = pricePerExcessMileage;
    }

    public int getVehicleQtyOnHand() {
        return vehicleQtyOnHand;
    }

    public void setVehicleQtyOnHand(int vehicleQtyOnHand) {
        this.vehicleQtyOnHand = vehicleQtyOnHand;
    }
}

