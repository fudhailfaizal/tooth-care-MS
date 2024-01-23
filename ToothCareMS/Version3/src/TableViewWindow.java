import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class TableViewWindow extends JFrame {
    private JComboBox<String> datePicker;
    private JButton filterButton;
    private JButton appointmentsButton;
    private JButton exitButton;
    private JButton patientsButton;
    private JPanel viewPanel;
    Appointment appointmentLogic = Appointment.getInstance();
    private JTextArea resultTextArea; // Add this line
    private JButton invoiceButton;

    public TableViewWindow() {
        initializeUI();
        setupActionListeners();
        populateDummyData();
        updateTable(); // Add this line
    }

    private void setupActionListeners() {
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
        patientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPatientsWindow();
            }
        });
        appointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAppointmentsWindow();
            }
        });
        invoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReceiptsWindow();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Task 2: Display data from Patient and Appointment classes
    private void displayDataFromClasses() {
        // Modified: Add an if statement to check if data is received
        if (appointmentLogic.getPatientListFromPatientClass().isEmpty()) {
            showError("Patients data not received");
        } else {
            System.out.println("Patient data received");
            appointmentLogic.getPatientListFromPatientClass().forEach(System.out::println);
        }

        // Modified: Add an if statement to check if data is received
        if (appointmentLogic.getAppointmentList().isEmpty()) {
            showError("Appointments data not received");
        } else {
            System.out.println("Appointment data received");
            appointmentLogic.printAppointmentList();
        }
    }
    private ArrayList<String> createNewList() {
        return new ArrayList<>();
    }

    private void extractData(ArrayList<String> newList) {
        appointmentLogic.getPatientListFromPatientClass().forEach(patientInfo -> {
            String[] patientDetails = patientInfo.split(", ");
            String extractedPatientInfo = String.format("[%s, %s, %s]", patientDetails[0], patientDetails[1], patientDetails[2]);
            newList.add(extractedPatientInfo);
        });

        appointmentLogic.getAppointmentList().forEach(appointmentInfo -> {
            String[] appointmentDetails = appointmentInfo.split(", ");
            String extractedAppointmentInfo = String.format("[%s, %s, %s, %s]", appointmentDetails[0], appointmentDetails[1],
                    appointmentDetails[2], appointmentDetails[3]);
            newList.add(extractedAppointmentInfo);
        });
    }

    private void displayExtractedInfo(ArrayList<String> newList) {
        System.out.println("Extracted Information:");
        for (String info : newList) {
            System.out.println(info);
        }
    }


    // Task 7: Display combined information with comments
//    private void displayCombinedInformation(String selectedDay) {
//        ArrayList<String> appointmentsFilteredByDate = appointmentLogic.getAppointmentsFilteredByDate(selectedDay);
//
//        // Modified: Add an if statement to check if data is received
//        if (appointmentsFilteredByDate.isEmpty()) {
//            showError("No appointments found for the selected day");
//        } else {
//            System.out.println("\nCombined Information:");
//            for (String appointmentInfo : appointmentsFilteredByDate) {
//                String[] appointmentDetails = appointmentInfo.split(", ");
//                String patientID = appointmentDetails[1].substring("Patient ID: ".length());
//
//                // Task 5: Find patient details based on patient ID
//                String patientInfo = appointmentLogic.findPatientDetails(patientID);
//
//                if (patientInfo != null) {
//                    System.out.println(appointmentInfo + " - " + patientInfo);
//                    resultTextArea.append(appointmentInfo + " - " + patientInfo + "\n");
//                } else {
//                    System.out.println(appointmentInfo + " - Patient not found");
//                    resultTextArea.append(appointmentInfo + " - Patient not found\n");
//                }
//            }
//        }
//    }
    // Task 7: Display combined information with comments
    private void displayCombinedInformation(String selectedDay) {
        ArrayList<String> appointmentsFilteredByDate = appointmentLogic.getAppointmentsFilteredByDate(selectedDay);

        // Modified: Add an if statement to check if data is received
        if (appointmentsFilteredByDate.isEmpty()) {
            showError("No appointments found for the selected day");
        } else {
            System.out.println("\nCombined Information:");
            for (String appointmentInfo : appointmentsFilteredByDate) {
                String[] appointmentDetails = appointmentInfo.split(", ");
                String patientID = appointmentDetails[1].substring("Patient ID: ".length());

                // Task 5: Find patient details based on patient ID
                String patientInfo = appointmentLogic.findPatientDetails(patientID);

                if (patientInfo != null) {
                    String combinedInfo = appointmentInfo + " - " + patientInfo;
                    System.out.println(combinedInfo);
                    resultTextArea.append(combinedInfo + "\n"); // Append a newline character
                } else {
                    String combinedInfo = appointmentInfo + " - Patient not found";
                    System.out.println(combinedInfo);
                    resultTextArea.append(combinedInfo + "\n"); // Append a newline character
                }
            }
        }
    }


    // Task 8: Show an error message in case of issues
    private void showError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void populateDummyData() {
        String[] daysOfWeek = {"Monday", "Wednesday", "Saturday", "Sunday"};
        datePicker.setModel(new DefaultComboBoxModel<>(daysOfWeek));
    }

    private void updateTable() {
        String selectedDay = (String) datePicker.getSelectedItem();
        ArrayList<String> appointmentsFilteredByDate = appointmentLogic.getAppointmentsFilteredByDate(selectedDay);

        // Display data from the Appointment class
        displayAppointmentsData();

        // Display combined information
        displayCombinedInformation(selectedDay);
    }

    // Display data from the Appointment class
    private void displayAppointmentsData() {
        // Modified: Add an if statement to check if data is received
        ArrayList<String> appointmentsList = appointmentLogic.getAppointmentList();
        if (appointmentsList.isEmpty()) {
            showError("Appointments data not received");
        } else {
            System.out.println("Appointment data received");
            appointmentLogic.printAppointmentList();
        }
    }



    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400)); // Initial size

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        datePicker = new JComboBox<>();
        filterButton = new JButton("Filter");
        topPanel.add(datePicker);
        topPanel.add(filterButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 0));
        appointmentsButton = new JButton("Appointments");
        patientsButton = new JButton("Patients");
        invoiceButton = new JButton("Receipts");
        exitButton = new JButton("Exit");
        buttonPanel.add(appointmentsButton);
        buttonPanel.add(patientsButton);
        buttonPanel.add(invoiceButton);
        buttonPanel.add(exitButton);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(topPanel, BorderLayout.NORTH);
        viewPanel.add(buttonPanel, BorderLayout.SOUTH);
        viewPanel.add(scrollPane, BorderLayout.CENTER);

        add(viewPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openAppointmentsWindow() {
        Patient patientInstance = Patient.getInstance();
        AppointmentsWindow appointmentsWindow = new AppointmentsWindow(patientInstance.getPatientList());
        appointmentsWindow.setTitle("Appointment Management");
        appointmentsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appointmentsWindow.setSize(700, 300);
        appointmentsWindow.setLocationRelativeTo(null);
        appointmentsWindow.setVisible(true);

        dispose(); // Close the current TableView window
    }

    private void openPatientsWindow() {
        PatientWindow patientWindow = new PatientWindow();
        patientWindow.setTitle("Patient Management");
        patientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientWindow.setSize(700, 300);
        patientWindow.setLocationRelativeTo(null);
        patientWindow.setVisible(true);
        dispose(); // Close the current TableView window
    }

    private void openReceiptsWindow() {
        HospitalManagementWindow hospitalManagementWindow = new HospitalManagementWindow();
        hospitalManagementWindow.setTitle("Hospital Management");
        hospitalManagementWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hospitalManagementWindow.setSize(700, 300);
        hospitalManagementWindow.setLocationRelativeTo(null);
        hospitalManagementWindow.setVisible(true);
        dispose(); // Close the current TableView window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TableViewWindow tableViewWindow = new TableViewWindow();
            tableViewWindow.setTitle("Appointment Management");
            tableViewWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tableViewWindow.setLocationRelativeTo(null);
            tableViewWindow.setVisible(true);
        });
    }
}
