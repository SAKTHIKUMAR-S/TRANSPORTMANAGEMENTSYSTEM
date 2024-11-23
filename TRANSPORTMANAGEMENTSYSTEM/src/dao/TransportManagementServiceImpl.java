package dao;

import entity.Vehicle;
import entity.Booking;
import entity.Driver;
import exception.VehicleNotFoundException;
import exception.BookingNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportManagementServiceImpl implements TransportManagementService {

    private Connection connection;

    // Constructor accepting the connection from the main method
    public TransportManagementServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO Vehicles (Model, Capacity, Type, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, vehicle.getModel());
            pstmt.setDouble(2, vehicle.getCapacity());
            pstmt.setString(3, vehicle.getType());
            pstmt.setString(4, vehicle.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        String query = "UPDATE Vehicles SET Model = ?, Capacity = ?, Type = ?, Status = ? WHERE VehicleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, vehicle.getModel());
            pstmt.setDouble(2, vehicle.getCapacity());
            pstmt.setString(3, vehicle.getType());
            pstmt.setString(4, vehicle.getStatus());
            pstmt.setInt(5, vehicle.getVehicleId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteVehicle(int vehicleId) throws VehicleNotFoundException {
        String query = "DELETE FROM Vehicles WHERE VehicleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, vehicleId);
            if (pstmt.executeUpdate() > 0) {
                return true;
            } else {
                throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean scheduleTrip(int vehicleId, int routeId, String departureDate, String arrivalDate) {
        String query = "INSERT INTO Trips (VehicleID, RouteID, DepartureDate, ArrivalDate, Status) VALUES (?, ?, ?, ?, 'Scheduled')";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, vehicleId);
            pstmt.setInt(2, routeId);
            pstmt.setString(3, departureDate);
            pstmt.setString(4, arrivalDate);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelTrip(int tripId) {
        String query = "UPDATE Trips SET Status = 'Cancelled' WHERE TripID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tripId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean bookTrip(int tripId, int passengerId, String bookingDate) {
        String query = "INSERT INTO Bookings (TripID, PassengerID, BookingDate, Status) VALUES (?, ?, ?, 'Confirmed')";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tripId);
            pstmt.setInt(2, passengerId);
            pstmt.setString(3, bookingDate);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelBooking(int bookingId) throws BookingNotFoundException {
        String query = "UPDATE Bookings SET Status = 'Cancelled' WHERE BookingID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, bookingId);
            if (pstmt.executeUpdate() > 0) {
                return true;
            } else {
                throw new BookingNotFoundException("Booking with ID " + bookingId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean allocateDriver(int tripId, int driverId) {
        String query = "UPDATE Trips SET DriverID = ? WHERE TripID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, driverId);
            pstmt.setInt(2, tripId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deallocateDriver(int tripId) {
        String query = "UPDATE Trips SET DriverID = NULL WHERE TripID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tripId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Booking> getBookingsByPassenger(int passengerId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Bookings WHERE PassengerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, passengerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("BookingID"),
                        rs.getInt("TripID"),
                        rs.getInt("PassengerID"),
                        rs.getTimestamp("BookingDate").toLocalDateTime(),
                        rs.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByTrip(int tripId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Bookings WHERE TripID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tripId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("BookingID"),
                        rs.getInt("TripID"),
                        rs.getInt("PassengerID"),
                        rs.getTimestamp("BookingDate").toLocalDateTime(),
                        rs.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Driver> getAvailableDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM Drivers WHERE DriverID NOT IN (SELECT DriverID FROM Trips WHERE DriverID IS NOT NULL)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                drivers.add(new Driver(
                        rs.getInt("DriverID"),
                        rs.getString("Name"),
                        rs.getString("License")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
}
