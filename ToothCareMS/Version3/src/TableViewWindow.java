import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private JTextField idField;

    private int totalTreatmentFees = 0;
    private final int REGISTRATION_FEE = 1000; // Registration fee is a constant
    private final Map<String, Integer> treatmentPrices = new HashMap<>();
    private int totalRegistrationFees = 0;

    public TableViewWindow() {
        initializeUI();
        setupActionListeners();
        populateDummyData();
        updateTable(); // Add this line

        // Added: Initialize treatment prices
        initializeTreatmentPrices();

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
                String registrationFeeText = JOptionPane.showInputDialog("Enter Registration Fee:");
                try {
                    int registrationFee = Integer.parseInt(registrationFeeText);

                    if (registrationFee < 0) {
                        showError("Registration fee cannot be negative");
                        return;
                    }

                    generateInvoice(registrationFee);
                } catch (NumberFormatException ex) {
                    showError("Invalid Registration Fee");
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void initializeTreatmentPrices() {
        treatmentPrices.put("Cleanings - 4500", 4500);
        treatmentPrices.put("Whitening - 35000", 35000);
        treatmentPrices.put("Filling - 4000", 4000);
        treatmentPrices.put("Nerve Filling - 25000", 25000);
        treatmentPrices.put("Root Canal Therapy - 25000", 25000);
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
    private void generateInvoice(int registrationFee) {
        String appointmentIdText = idField.getText().trim();

        if (appointmentIdText.isEmpty()) {
            showError("Please enter an Appointment ID");
            return;
        }

        try {
            int appointmentId = Integer.parseInt(appointmentIdText);

            // Modified: Check if the appointment exists for the provided ID
            if (!appointmentLogic.appointmentExists(String.valueOf(appointmentId))) {
                showError("No appointment found for the provided ID");
                return;
            }

            // Modified: Extract treatment type from the combo box selection
            String selectedTreatment = (String) JOptionPane.showInputDialog(this,
                    "Select Treatment", "Treatment Selection", JOptionPane.PLAIN_MESSAGE,
                    null, treatmentPrices.keySet().toArray(), null);

            // Validate selected treatment
            if (selectedTreatment == null || selectedTreatment.isEmpty()) {
                showError("Invalid or no treatment selected");
                return;
            }

            // Modified: Calculate treatment fee based on selected treatment
            int treatmentFee = treatmentPrices.get(selectedTreatment);

            int totalFee = treatmentFee + registrationFee;

            appointmentLogic.generateInvoice(
                    String.valueOf(appointmentId),
                    "DefaultPatientName",  // Replace with the actual patient name
                    selectedTreatment,
                    treatmentFee,
                    registrationFee
            );

            showInvoiceDetails(appointmentId, selectedTreatment, treatmentFee, registrationFee, totalFee);
        } catch (NumberFormatException e) {
            showError("Invalid Appointment ID");
        }
    }


    private void showInvoiceDetails(int appointmentId, String treatmentType, int treatmentFee, int registrationFee, int totalFee) {
        String invoiceDetails = String.format(
                "Appointment ID: %d\nPatient Name: DefaultPatientName\nTreatment: %s\nTreatment Fee: %d\nRegistration Fee: %d\nTotal Fee: %d",
                appointmentId, treatmentType, treatmentFee, registrationFee, totalFee);

        JOptionPane.showMessageDialog(this, invoiceDetails, "Invoice Details", JOptionPane.INFORMATION_MESSAGE);
    }



    // Task 9: Process the payment and update totals
    private void processPaymentAndUpdateTotals() {
        // Extract treatment fee from the invoice
        int treatmentFee = extractTreatmentFeeFromInvoice();

        // Add treatment fee to the total treatment fees
        totalTreatmentFees += treatmentFee;

        // Add registration fee to the total registration fees
        totalRegistrationFees += REGISTRATION_FEE;

        // Calculate the total amount
        int totalAmount = totalTreatmentFees + totalRegistrationFees;

        // Display the updated totals in the console
        System.out.println("Total Treatment Fees: " + totalTreatmentFees);
        System.out.println("Total Registration Fees: " + totalRegistrationFees);
        System.out.println("Total Amount: " + totalAmount);
    }

    // Task 10: Extract treatment fee from the invoice
    private int extractTreatmentFeeFromInvoice() {
        // Search for the Treatment Fee pattern in the invoice
        String invoice = "";  // Replace this with the actual invoice variable if available
        String treatmentFeePattern = "Treatment Fee: (\\d+)";
        Pattern pattern = Pattern.compile(treatmentFeePattern);
        Matcher matcher = pattern.matcher(invoice);

        // If a match is found, extract the treatment fee
        if (matcher.find()) {
            String treatmentFeeStr = matcher.group(1);
            return Integer.parseInt(treatmentFeeStr);
        }

        // Return 0 if no match is found (should not happen if the invoice is generated correctly)
        return 0;
    }

    // Helper method to get treatment fee based on treatment type
    private int getTreatmentFee(String treatmentType) {
        // Map to store treatment fees
        Map<String, Integer> treatmentFees = new HashMap<>();
        treatmentFees.put("Cleanings", 4500);
        treatmentFees.put("Whitening", 35000);
        treatmentFees.put("Filling", 4000);
        treatmentFees.put("Nerve Filling", 25000);
        treatmentFees.put("Root Canal Therapy", 25000);

        // Get treatment fee from the map
        return treatmentFees.getOrDefault(treatmentType, 0);
    }

    // Helper method to update total (here, I'll use a Map for demonstration)
    private void updateTotal(int amount) {
        // Map to store totals
        Map<String, Integer> totals = new HashMap<>();
        totals.put("TotalAmount", amount);

        // Display the updated total (here, I'm just printing it)
        System.out.println("Updated Total: " + totals.get("TotalAmount"));
    }


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
        idField = new JTextField(10); // Add this line for the idField
        topPanel.add(datePicker);
        topPanel.add(filterButton);
        topPanel.add(new JLabel("Appointment ID:")); // Label for idField
        topPanel.add(idField); // Add idField to the topPanel

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
