package funcPackage;

import java.util.LinkedList;
import java.util.Scanner;

public class Reservation {

    class PatientRecord {
        int appointmentID;
        String patientName;
        String address;
        String telephoneNumber;

        public PatientRecord(int appointmentID, String patientName, String address, String telephoneNumber) {
            this.appointmentID = appointmentID;
            this.patientName = patientName;
            this.address = address;
            this.telephoneNumber = telephoneNumber;
        }
    }

    LinkedList<PatientRecord> patientRecords = new LinkedList<>();

    public void addPatientRecord(int appointmentID, String patientName, String address, String telephoneNumber) {
        PatientRecord newRecord = new PatientRecord(appointmentID, patientName, address, telephoneNumber);
        patientRecords.add(newRecord);
    }

    public PatientRecord searchByAppointmentID(int appointmentID) {
        for (PatientRecord record : patientRecords) {
            if (record.appointmentID == appointmentID) {
                return record;
            }
        }
        return null;
    }

    public void displayAllRecords() {
        if (patientRecords.isEmpty()) {
            System.out.println("No patient records available.");
            return;
        }

        for (PatientRecord record : patientRecords) {
            System.out.println("Appointment ID: " + record.appointmentID);
            System.out.println("Patient Name: " + record.patientName);
            System.out.println("Address: " + record.address);
            System.out.println("Telephone Number: " + record.telephoneNumber);
            System.out.println();
        }
    }

    public static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the reservation menu!");

        System.out.println("Enter Appointment ID");
        int appointmentID = sc.nextInt();
        sc.nextLine();  // Consume newline character

        System.out.println("Enter Patient Name");
        String patientName = sc.nextLine();

        System.out.println("Enter Patient Address");
        String patientAdd = sc.nextLine();

        System.out.println("Enter Patient Contact Number");
        String patientCont = sc.nextLine();

        Reservation pd = new Reservation();
        pd.addPatientRecord(appointmentID, patientName, patientAdd, patientCont);
        pd.displayAllRecords();
    }
}
