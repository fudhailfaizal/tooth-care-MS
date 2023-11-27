import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Patient {
    private int patientID = 0;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField numberField;
    private JButton enter;

    private LinkedList<Patient> patientList;


    public Patient(LinkedList<Patient> patientList) {
        this.patientList = patientList;
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                int phoneNumber = Integer.parseInt(numberField.getText());

                // Auto Increment Patient ID
                patientID++;

                Patient newPatient = new Patient(patientID, name, address, phoneNumber);
                patientList.add(newPatient);

                // Clear fields method after entering
                clearFields();
            }
        });
    }

    public Patient(int patientID, String name, String address, int phoneNumber) {
        this.patientID = patientID;
        this.nameField = new JTextField(name);
        this.addressField = new JTextField(address);
        this.numberField = new JTextField(phoneNumber);
    }

    private void clearFields() {
        nameField.setText("");
        addressField.setText("");
        numberField.setText("");
    }
}

class Treatment {
    public int treatmentID;
    public String treatmentName;
    public double price;


    public Treatment(int treatmentID, String treatmentName, double price) {
        this.treatmentID = treatmentID;
        this.treatmentName = treatmentName;
        this.price = price;
    }
}