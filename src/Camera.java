public class Camera implements Comparable<Camera> {
    private int cameraId;
    private String brand;
    private String model;
    private double rent;
    private String status;

    public int getCameraId() {
        return cameraId;
    }
    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getRent() {
        return rent;
    }
    public void setRent(double rent) {
        this.rent = rent;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public int compareTo(Camera cam) {
        if(this.getCameraId() < cam.cameraId) {
            return -1;
        }
        if(this.getCameraId() > cam.cameraId) {
            return 1;
        }
        return 0;
    }
}
