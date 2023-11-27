import java.io.FilterOutputStream;
import java.util.LinkedList;

public class HospitalManagement {
    private LinkedList<Patient> patients;
    private LinkedList<Appointments> appointments;
    private LinkedList<Treatment> treatments;
    private final double registrationFee;


    public HospitalManagement(double registrationFee) {

        this.patients = new LinkedList<>();
        this.appointments = new LinkedList<>();
        this.treatments = new LinkedList<>();
        this.registrationFee = registrationFee;
        initializeTreatments();
    }

    private void initializeTreatments() {
        Treatment cleaning = new Treatment(1, "Cleanings", 0.00);
        Treatment whitening = new Treatment(2, "Whitening", 0.00);
        Treatment filling = new Treatment(1, "Filling", 0.00);
        Treatment nerveFilling = new Treatment(1, "Nerve Filling", 0.00);
        Treatment rootCanalTherapy = new Treatment(1, "Root Canal Therapy", 0.00);

        treatments.add(cleaning);
        treatments.add(whitening);
        treatments.add(filling);
        treatments.add(nerveFilling);
        treatments.add(rootCanalTherapy);
    }



}
