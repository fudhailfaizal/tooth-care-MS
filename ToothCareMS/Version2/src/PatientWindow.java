public class PatientWindow {
    private int patientID;
    private String name;
    private String address;
    private int phoneNumber;

    public PatientWindow(int patientID, String name, String address, int phoneNumber) {
        this.patientID = patientID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters for patient information
    // ...

    @Override
    public String toString() {
        return "PatientWindow ID: " + patientID + ", Name: " + name +
                ", Address: " + address + ", Phone Number: " + phoneNumber;
    }
}
