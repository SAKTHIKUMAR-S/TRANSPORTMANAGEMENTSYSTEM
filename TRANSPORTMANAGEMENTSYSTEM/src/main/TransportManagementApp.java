package main;

import dao.TransportManagementServiceImpl;
import entity.Booking;
import entity.Driver;
import entity.Vehicle;
import exception.VehicleNotFoundException;
import util.DBConnUtil;
import java.sql.Connection;
import java.util.Scanner;
import java.util.List;

public class TransportManagementApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        try {
            // Establish database connection
        	connection = DBConnUtil.getConnection();
            System.out.println("Database connection established!");

            // Step 2: Initialize the TransportManagementServiceImpl with the database connection
            TransportManagementServiceImpl service = new TransportManagementServiceImpl(connection);

            // Main application loop
            while (true) {
                // Display menu options
                System.out.println("\n=== Transport Management System ===");
                System.out.println("1. Add Vehicle");
                System.out.println("2. Update Vehicle");
                System.out.println("3. Delete Vehicle");
                System.out.println("4. Schedule Trip");
                System.out.println("5. Cancel Trip");
                System.out.println("6. Book Trip");
                System.out.println("7. Cancel Booking");
                System.out.println("8. Allocate Driver");
                System.out.println("9. Deallocate Driver");
                System.out.println("10. Get Bookings by Passenger");
                System.out.println("11. Get Bookings by Trip");
                System.out.println("12. Get Available Drivers");
                System.out.println("13. Exit");

                // Get user input
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: {
                        // Add Vehicle
                        System.out.println("Enter vehicle model:");
                        String model = scanner.nextLine();
                        System.out.println("Enter vehicle capacity:");
                        double capacity = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        System.out.println("Enter vehicle type (e.g., Truck, Bus, Van):");
                        String type = scanner.nextLine();
                        System.out.println("Enter vehicle status (e.g., Available, Maintenance):");
                        String status = scanner.nextLine();

                        Vehicle vehicle = new Vehicle(0, model, capacity, type, status);
                        boolean result = service.addVehicle(vehicle);
                        System.out.println(result ? "Vehicle added successfully." : "Failed to add vehicle.");
                        break;
                    }
                    case 2: {
                        // Update Vehicle
                        System.out.println("Enter vehicle ID to update:");
                        int vehicleId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter new model:");
                        String model = scanner.nextLine();
                        System.out.println("Enter new capacity:");
                        double capacity = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        System.out.println("Enter new type (e.g., Truck, Bus, Van):");
                        String type = scanner.nextLine();
                        System.out.println("Enter new status (e.g., Available, Maintenance):");
                        String status = scanner.nextLine();

                        Vehicle updatedVehicle = new Vehicle(vehicleId, model, capacity, type, status);
                        boolean result = service.updateVehicle(updatedVehicle);
                        System.out.println(result ? "Vehicle updated successfully." : "Failed to update vehicle.");
                        break;
                    }
                    case 3: {
                        // Delete Vehicle
                        System.out.println("Enter vehicle ID to delete:");
                        int vehicleId = scanner.nextInt();
                        boolean result;
                        try {
                            result = service.deleteVehicle(vehicleId);
                            System.out.println(result ? "Vehicle deleted successfully." : "Failed to delete vehicle.");
                        } catch (VehicleNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        // Schedule Trip
                        System.out.println("Enter vehicle ID:");
                        int vehicleId = scanner.nextInt();
                        System.out.println("Enter route ID:");
                        int routeId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter departure date (yyyy-MM-dd HH:mm):");
                        String departureDate = scanner.nextLine();
                        System.out.println("Enter arrival date (yyyy-MM-dd HH:mm):");
                        String arrivalDate = scanner.nextLine();

                        boolean result = service.scheduleTrip(vehicleId, routeId, departureDate, arrivalDate);
                        System.out.println(result ? "Trip scheduled successfully." : "Failed to schedule trip.");
                        break;
                    }
                    case 5: {
                        // Cancel Trip
                        System.out.println("Enter trip ID to cancel:");
                        int tripId = scanner.nextInt();
                        boolean result = service.cancelTrip(tripId);
                        System.out.println(result ? "Trip cancelled successfully." : "Failed to cancel trip.");
                        break;
                    }
                    case 6: {
                        // Book Trip
                        System.out.println("Enter trip ID:");
                        int tripId = scanner.nextInt();
                        System.out.println("Enter passenger ID:");
                        int passengerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter booking date (yyyy-MM-dd HH:mm):");
                        String bookingDate = scanner.nextLine();

                        boolean result = service.bookTrip(tripId, passengerId, bookingDate);
                        System.out.println(result ? "Trip booked successfully." : "Failed to book trip.");
                        break;
                    }
                    case 7: {
                        // Cancel Booking
                        System.out.println("Enter booking ID to cancel:");
                        int bookingId = scanner.nextInt();
                        boolean result = service.cancelBooking(bookingId);
                        System.out.println(result ? "Booking cancelled successfully." : "Failed to cancel booking.");
                        break;
                    }
                    case 8: {
                        // Allocate Driver
                        System.out.println("Enter trip ID:");
                        int tripId = scanner.nextInt();
                        System.out.println("Enter driver ID:");
                        int driverId = scanner.nextInt();

                        boolean result = service.allocateDriver(tripId, driverId);
                        System.out.println(result ? "Driver allocated successfully." : "Failed to allocate driver.");
                        break;
                    }
                    case 9: {
                        // Deallocate Driver
                        System.out.println("Enter trip ID:");
                        int tripId = scanner.nextInt();

                        boolean result = service.deallocateDriver(tripId);
                        System.out.println(result ? "Driver deallocated successfully." : "Failed to deallocate driver.");
                        break;
                    }
                    case 10: {
                        // Get Bookings by Passenger
                        System.out.println("Enter passenger ID:");
                        int passengerId = scanner.nextInt();
                        List<Booking> bookings = service.getBookingsByPassenger(passengerId);
						if (bookings.isEmpty()) {
						    System.out.println("No bookings found for passenger.");
						} else {
						    bookings.forEach(System.out::println);
						}
                        break;
                    }
                    case 11: {
                        // Get Bookings by Trip
                        System.out.println("Enter trip ID:");
                        int tripId = scanner.nextInt();
                        List<Booking> bookings = service.getBookingsByTrip(tripId);
						if (bookings.isEmpty()) {
						    System.out.println("No bookings found for trip.");
						} else {
						    bookings.forEach(System.out::println);
						}
                        break;
                    }
                    case 12: {
                        // Get Available Drivers
                        List<Driver> availableDrivers = service.getAvailableDrivers();
                        if (availableDrivers.isEmpty()) {
                            System.out.println("No available drivers.");
                        } else {
                            availableDrivers.forEach(System.out::println);
                        }
                        break;
                    }
                    case 13: {
                        // Exit
                        System.out.println("Exiting the application...");
                        scanner.close();
                        return;
                    }
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
