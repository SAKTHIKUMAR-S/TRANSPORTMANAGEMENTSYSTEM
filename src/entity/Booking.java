package entity;

import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private int tripId;
    private int passengerId;
    private LocalDateTime bookingDate;
    private String status;

    public Booking() {
    }

    public Booking(int bookingId, int tripId, int passengerId, LocalDateTime bookingDate, String status) {
        this.bookingId = bookingId;
        this.tripId = tripId;
        this.passengerId = passengerId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
