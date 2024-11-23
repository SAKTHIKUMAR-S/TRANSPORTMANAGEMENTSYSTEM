package entity;

public class Driver {
    private int driverId;
    private String name;
    private String license;

    public Driver() {
    }

    public Driver(int driverId, String name, String license) {
        this.driverId = driverId;
        this.name = name;
        this.license = license;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
