import java.util.ArrayList;

public class HospitalManagement {
    public static void main(String[] args) {
        // Create an instance of the Patient class
        Patient patient = new Patient();

        // Add patients to the patient list
        patient.addPatient("John Doe", "123 Main St", 1234567890);
        patient.addPatient("Alice Smith", "456 Elm St", 987654321);

        // Retrieve the list of patients
        ArrayList<String> patientsList = patient.getPatientList();

        // Display the list of patients (for demonstration purposes)
        for (String patientInfo : patientsList) {
            System.out.println(patientInfo);
        }

        // Here you can coordinate other aspects of your hospital management system
        // such as handling appointments, treatments, staff, etc.
    }
}
