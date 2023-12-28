import java.util.ArrayList;

public class HospitalManagement {
    public static void main(String[] args) {
        // Create an instance of the Patient class
        Patient patient = new Patient();

        // Add patients to the patient list
        patient.setName("John Doe");
        patient.setAddress("123 Main St");
        patient.setPhoneNumber("1234567890");
        patient.addPatient();

        patient.setName("Alice Smith");
        patient.setAddress("456 Elm St");
        patient.setPhoneNumber("987654321");
        patient.addPatient();

        // Retrieve the list of patients
        ArrayList<Patient> patientsList = Patient.patientList;

        // Display the list of patients (for demonstration purposes)
        for (Patient patientInfo : patientsList) {
            System.out.println(patientInfo.toString());
        }

        // Here you can coordinate other aspects of your hospital management system
        // such as handling appointments, treatments, staff, etc.
    }
}
