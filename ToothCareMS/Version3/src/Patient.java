import java.util.ArrayList;

public class Patient {
    private int patientID = 0;
    private ArrayList<String> patientList = new ArrayList<>();

    public void addPatient(String name, String address, int phoneNumber) {
        patientID++;
        String patientInfo = String.format("Patient ID: %d, Name: %s, Address: %s, Phone Number: %s",
                patientID, name, address, phoneNumber);
        patientList.add(patientInfo);
    }
    public String findPatientDetails(String patientID) {
        for (String patientInfo : patientList) {
            if (patientInfo.contains("Patient ID: " + patientID)) {
                return patientInfo;
            }
        }
        return null; // Or an empty string or a message indicating not found
    }

    public ArrayList<String> getPatientList() {
        return patientList;
    }
}
