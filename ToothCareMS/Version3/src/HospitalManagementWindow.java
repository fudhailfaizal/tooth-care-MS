import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class HospitalManagementWindow extends JFrame {
    // GUI components
    private JTable appointmentsTable;
    private JPanel managementPanel;
    private JTextField AppointmentID;
    private JButton generateInvoiceButton;
    private JButton searchButton;
    private JButton addButton;
    private JButton patientsButton;
    private JButton exitButton;
    private JComboBox datePicker;

    // Logic component
    private Appointment appointmentLogic = new Appointment(); // Managing appointments

    // Constructor
    public HospitalManagementWindow() {
        initializeUI();
        setupAppointmentsTable();
    }

    // Initialize the UI
    private void initializeUI() {
        setContentPane(managementPanel);
        setTitle("Hospital Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Setup appointments table
    private void setupAppointmentsTable() {
        String[] columns = {"Appointment ID", "Patient ID", "Treatment ID", "Date", "Time"};
        ArrayList<String> appointmentsData = appointmentLogic.getAppointmentList(); // Retrieve appointment data

        String[][] data = new String[appointmentsData.size()][5];
        for (int i = 0; i < appointmentsData.size(); i++) {
            String[] appointment = appointmentsData.get(i).split(", ");
            data[i] = appointment;
        }

        // Create a table model and populate it with appointment data
        DefaultTableModel model = new DefaultTableModel(data, columns);
        appointmentsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(appointmentsTable);

        // Add the table to the panel
        managementPanel.add(scrollPane);
    }

    // Other methods for handling button actions, etc.

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HospitalManagementWindow hospitalManagementWindow = new HospitalManagementWindow();
            hospitalManagementWindow.setTitle("Hospital Management");
            hospitalManagementWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            hospitalManagementWindow.setSize(800, 600);
            hospitalManagementWindow.setLocationRelativeTo(null);
            hospitalManagementWindow.setVisible(true);
        });
    }
}
