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

    public ArrayList<String> getPatientList() {
        return patientList;
    }
}
