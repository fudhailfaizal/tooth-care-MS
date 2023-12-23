import javax.swing.*;
import java.util.LinkedList;

public class HospitalManagement extends JFrame {
    private LinkedList<PatientWindow> patients;
    private LinkedList<Appointments> appointments;
    private LinkedList<Treatment> treatments;
    private final double registrationFee = 1000;
    private JPanel hospitalPanel;
    private JTextField textField1;
    private JButton showInvoicesButton;
    private JButton createInvoiceButton;
    private JButton backButton;


    public HospitalManagement() {

        //UI Setup
        setContentPane(hospitalPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        this.patients = new LinkedList<>();
        this.appointments = new LinkedList<>();
        this.treatments = new LinkedList<>();
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

    public static void main(String[] args) {
        new HospitalManagement();
    }



}
