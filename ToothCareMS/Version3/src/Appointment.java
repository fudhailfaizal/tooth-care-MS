import java.util.ArrayList;

public class Appointment {
    private ArrayList<String> patientList = new ArrayList<>();
    private ArrayList<String> treatmentList = new ArrayList<>();
    private ArrayList<String> appointmentList = new ArrayList<>();

    private Patient patient = new Patient();

    public void addPatient(String patientInfo) {
        patientList.add(patientInfo);
    }

    public void addTreatment(String treatmentInfo) {
        treatmentList.add(treatmentInfo);
    }

    public void scheduleAppointment(int patientID, String treatmentID, String date, String time) {
        int newAppointmentID = appointmentList.size() + 1;
        String appointmentInfo = String.format("Appointment ID: %d, Patient ID: %s, Treatment ID: %s, Date: %s, Time: %s",
                newAppointmentID, patientID, treatmentID, date, time);
        appointmentList.add(appointmentInfo);
    }

    public ArrayList<String> getAppointmentList() {
        return appointmentList;
    }

    public ArrayList<Patient> getPatientListFromPatientClass() {
        return Patient.patientList;
    }

    public Patient findPatientDetails(int patientID, ArrayList<Patient> patientList) {
        for (Patient patient : patientList) {// Spl
                if (patientID == patient.getPatientID()) {
                    return patient;
                }
        }
        return null;
    }
}
