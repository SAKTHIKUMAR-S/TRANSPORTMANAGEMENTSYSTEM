package entity;

import java.util.Date;

public class Trip {
    private int tripID;
    private int vehicleID;
    private int routeID;
    private Date departureDate;
    private Date arrivalDate;
    private String status;
    private String tripType;
    private int maxPassengers;

    // Default constructor
    public Trip() {}

    // Parameterized constructor
    public Trip(int tripID, int vehicleID, int routeID, Date departureDate, Date arrivalDate, 
                String status, String tripType, int maxPassengers) {
        this.tripID = tripID;
        this.vehicleID = vehicleID;
        this.routeID = routeID;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.status = status;
        this.tripType = tripType;
        this.maxPassengers = maxPassengers;
    }

    // Getters and Setters
    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }
}
