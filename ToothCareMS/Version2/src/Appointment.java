public class Appointment {
    private int appointmentID;
    private String patientID;
    private int treatmentID;
    private String date;
    private String time;

    public Appointment(int appointmentID, String patientID, int treatmentID, String date, String time) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.treatmentID = treatmentID;
        this.date = date;
        this.time = time;
    }

    // Getters and setters for appointment information
    // ...

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", PatientWindow ID: " + patientID +
                ", Treatment ID: " + treatmentID + ", Date: " + date + ", Time: " + time;
    }
}
