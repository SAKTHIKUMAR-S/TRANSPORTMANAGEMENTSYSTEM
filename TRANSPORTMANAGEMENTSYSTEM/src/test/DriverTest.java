package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import entity.Vehicle;
import entity.Trip;

public class DriverTest {

    private Vehicle vehicle;
    private Trip trip;

    @BeforeEach
    public void setUp() {
        // Set up the test data for each test method
        vehicle = new Vehicle(5, "Ford Transit", 50, "Bus", "Active");
        trip = new Trip(105, 5, 10, new Date(), new Date(), "Scheduled", "One-way", 50);
    }

    @Test
    public void testVehicleCreation() {
        // Test if the vehicle object was created correctly
        assertNotNull(vehicle, "Vehicle should not be null");
        assertEquals(5, vehicle.getVehicleId(), "Vehicle ID should be 5");
        assertEquals("Ford Transit", vehicle.getModel(), "Vehicle model should be 'Ford Transit'");
        assertEquals(50, vehicle.getCapacity(), "Vehicle capacity should be 50");
        assertEquals("Bus", vehicle.getType(), "Vehicle type should be 'Bus'");
        assertEquals("Active", vehicle.getStatus(), "Vehicle status should be 'Active'");
    }

    @Test
    public void testTripCreation() {
        // Test if the trip object was created correctly
        assertNotNull(trip, "Trip should not be null");
        assertEquals(105, trip.getTripID(), "Trip ID should be 105");
        assertEquals(5, trip.getVehicleID(), "Vehicle ID should be 5");
        assertEquals(10, trip.getRouteID(), "Route ID should be 10");
        assertEquals("Scheduled", trip.getStatus(), "Trip status should be 'Scheduled'");
        assertEquals("One-way", trip.getTripType(), "Trip type should be 'One-way'");
        assertEquals(50, trip.getMaxPassengers(), "Max passengers should be 50");
    }

    @Test
    public void testVehicleStatusChange() {
        // Test changing the vehicle status
        vehicle.setStatus("Inactive");
        assertEquals("Inactive", vehicle.getStatus(), "Vehicle status should be 'Inactive'");
    }

    @Test
    public void testTripStatusChange() {
        // Test changing the trip status
        trip.setStatus("Completed");
        assertEquals("Completed", trip.getStatus(), "Trip status should be 'Completed'");
    }

    @Test
    public void testVehicleCapacity() {
        // Test vehicle capacity
        vehicle.setCapacity(60);
        assertEquals(60, vehicle.getCapacity(), "Vehicle capacity should be updated to 60");
    }

    @Test
    public void testTripMaxPassengers() {
        // Test maximum passengers for the trip
        trip.setMaxPassengers(60);
        assertEquals(60, trip.getMaxPassengers(), "Max passengers should be updated to 60");
    }
}
