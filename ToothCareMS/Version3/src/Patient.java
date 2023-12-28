import java.util.ArrayList;

public class Patient {
    private int patientID;
    private String name;
    private String address;
    private String phoneNumber;

    public int getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    static public ArrayList<Patient> patientList = new ArrayList<>();

    public void addPatient() {
        patientID = Patient.patientList.size() + 1;
        Patient.patientList.add(this);
    }

    public Patient findPatientDetails(int patientID) {
        for (var patientInfo : patientList) {
            if (patientInfo.getPatientID() == patientID) {
                return patientInfo;
            }
        }
        return null; // Or an empty string or a message indicating not found
    }

}
